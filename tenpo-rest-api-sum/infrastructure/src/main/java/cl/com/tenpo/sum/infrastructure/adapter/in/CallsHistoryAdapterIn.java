package cl.com.tenpo.sum.infrastructure.adapter.in;

import cl.com.tenpo.application.port.in.ICallsHistoryPortIn;
import cl.com.tenpo.domain.configuration.annotations.AdapterIn;
import cl.com.tenpo.domain.configuration.exception.TenpoException;
import cl.com.tenpo.domain.dto.CallsHistoryDTO;
import cl.com.tenpo.domain.dto.ResponseDTO;
import cl.com.tenpo.domain.enumeration.CallsHistoryEnumeration;
import cl.com.tenpo.domain.mapper.ICallsHistoryMapper;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

@AdapterIn
@RestController
@Tag(name = "Calls History",
        description = "This are a rest api build to solve technical test during the searching of Tenpo company " +
                "for a tech lead with solid knowledge in java language")
@RequestMapping(value = "api/history/")
public class CallsHistoryAdapterIn {

    @Autowired
    private ICallsHistoryMapper iCallsHistoryMapper;

    @Autowired
    private ICallsHistoryPortIn iCallsHistoryPortIn;

    @Value(value = "${message.success}")
    private String messageSuccess;

    @Value(value = "${message.problems.with.request.limit}")
    private String messageProblemsWithRequestLimit;

    @Value(value = "${parameter.api.request.call.limit}")
    private Integer parameterApiRequestCallLimit;

    @Value(value = "${parameter.api.request.call.limit.tokens}")
    private Integer parameterApiRequestCallLimitTokens;

    @Value(value = "${parameter.api.request.call.limit.minutes}")
    private Integer parameterApiRequestCallLimitMinutes;


    private final Bucket bucket;

    public CallsHistoryAdapterIn() {
        Bandwidth limit = Bandwidth.classic(Objects.requireNonNullElse(parameterApiRequestCallLimit, 3),
                          Refill.greedy(Objects.requireNonNullElse(parameterApiRequestCallLimitTokens, 3),
                          Duration.ofMinutes(Objects.requireNonNullElse(parameterApiRequestCallLimitMinutes, 1))));

        this.bucket = Bucket.builder().addLimit(limit).build();
    }

    @ApiOperation(value = "Deigned to return Calls History Paginated", tags = "Calls History")
    @GetMapping(value = "calls-history-paginated")
    public ResponseEntity<ResponseDTO<List<CallsHistoryDTO>>> callsHistoryPaginated(HttpServletRequest request,  @RequestParam(value = "pageNumber", required = false, defaultValue = "0") @ApiParam(value = "Page number where user wish locate") Integer pageNumber,
                                                                                    @RequestParam(value = "numberOfRecords", required = false, defaultValue = "5") @ApiParam(value = "Amount records wish by api that call the backend") Integer numberOfRecords,
                                                                                    @RequestParam(value = "descendingOrder", required = false, defaultValue = "false") @ApiParam(value = "Wish sort records in order descending") Boolean descendingOrder,
                                                                                    @RequestParam(value = "sortBy", required = false, defaultValue = "DATE_AND_HOUR") @ApiParam(value = "By which field do you want to sort the records") CallsHistoryEnumeration sortBy) throws TenpoException {
        ResponseDTO<List<CallsHistoryDTO>> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage(messageSuccess);
        responseDTO.setStatus(HttpStatus.OK);

        if (Objects.requireNonNull(bucket.tryConsume(1))) {
            responseDTO.setData(iCallsHistoryMapper.ListModelInDTOList(iCallsHistoryPortIn.callsHistoryPaginated(pageNumber, numberOfRecords, descendingOrder, sortBy.getValue())));
            return new ResponseEntity<>(responseDTO, responseDTO.getStatus());
        }

        responseDTO.setMessage(messageProblemsWithRequestLimit);
        responseDTO.setStatus(HttpStatus.TOO_MANY_REQUESTS);
        return new ResponseEntity<>(responseDTO, responseDTO.getStatus());
    }

}
