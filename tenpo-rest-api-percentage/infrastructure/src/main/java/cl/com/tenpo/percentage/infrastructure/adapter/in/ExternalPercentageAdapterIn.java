package cl.com.tenpo.percentage.infrastructure.adapter.in;

import cl.com.tenpo.percentage.aplication.port.in.IExternalPercentagePortIn;
import cl.com.tenpo.percentage.domain.configuration.annotations.AdapterIn;
import cl.com.tenpo.percentage.domain.configuration.exceptions.TenpoException;
import cl.com.tenpo.percentage.domain.dto.ExternalPercentageDTO;
import cl.com.tenpo.percentage.domain.dto.ResponseDTO;
import cl.com.tenpo.percentage.domain.mapper.IExternalPercentageMapper;
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
import java.util.Objects;

@AdapterIn
@RestController
@Tag(name = "External Percentage",
        description = "This are a rest api build to solve technical test during the searching of Tenpo company " +
                "for a tech lead with solid knowledge in java language")
@RequestMapping(value = "api/percentage/")
class ExternalPercentageAdapterIn {

    @Autowired
    private IExternalPercentageMapper iExternalPercentageMapper;

    @Autowired
    private IExternalPercentagePortIn iExternalPercentagePortIn;

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

    public ExternalPercentageAdapterIn() {
        Bandwidth limit = Bandwidth.classic(Objects.requireNonNullElse(parameterApiRequestCallLimit, 3),
                          Refill.greedy(Objects.requireNonNullElse(parameterApiRequestCallLimitTokens, 3),
                          Duration.ofMinutes(Objects.requireNonNullElse(parameterApiRequestCallLimitMinutes, 1))));

        this.bucket = Bucket.builder().addLimit(limit).build();
    }

    @ApiOperation(value = "This api is designed for make that number one make sum with number two and after applying the n% to sum then return number calculated to caller to them can end operation", tags = "External Percentage")
    @GetMapping(value = "sum-two-numbers-and-return-value-applying-percentage-parametrized")
    public ResponseEntity<ResponseDTO<ExternalPercentageDTO>> sumTwoNumbersAndAndReturnValueApplyingPercentageParametrized(HttpServletRequest request,
                                                                                                  @ApiParam(value = "Number One", required = true, example = "123456789") @RequestParam(value = "numberOne") Double numberOne,
                                                                                                  @ApiParam(value = "Number Two", required = true, example = "987654321") @RequestParam(value = "numberTwo") Double numberTwo) throws TenpoException {
        ResponseDTO<ExternalPercentageDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage(messageSuccess);
        responseDTO.setStatus(HttpStatus.OK);

        if (Objects.requireNonNull(bucket.tryConsume(1))) {
            responseDTO.setData(iExternalPercentageMapper.ModelInDTO(iExternalPercentagePortIn.sumTwoNumbersAndAndReturnValueApplyingPercentageParametrized(numberOne, numberTwo)));
            return new ResponseEntity<>(responseDTO, responseDTO.getStatus());
        }

        responseDTO.setMessage(messageProblemsWithRequestLimit);
        responseDTO.setStatus(HttpStatus.TOO_MANY_REQUESTS);
        return new ResponseEntity<>(responseDTO, responseDTO.getStatus());
    }

}
