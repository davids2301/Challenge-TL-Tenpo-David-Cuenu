package cl.com.tenpo.sum.infrastructure.adapter.in;

import cl.com.tenpo.application.port.in.ISumTwoNumbersPortIn;
import cl.com.tenpo.domain.configuration.annotations.AdapterIn;
import cl.com.tenpo.domain.configuration.exception.TenpoException;
import cl.com.tenpo.domain.dto.ResponseDTO;
import cl.com.tenpo.domain.dto.SumTwoNumbersDTO;
import cl.com.tenpo.domain.mapper.ISumTwoNumbersMapper;
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
@Tag(name = "Sum Two Numbers",
        description = "This are a rest api build to solve technical test during the searching of Tenpo company " +
                "for a tech lead with solid knowledge in java language")
@RequestMapping(value = "api/sum/")
public class SumTwoNumbersAdapterIn {

    @Autowired
    private ISumTwoNumbersMapper iSumTwoNumbersMapper;

    @Autowired
    private ISumTwoNumbersPortIn iSumTwoNumbersPortIn;

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

    public SumTwoNumbersAdapterIn() {
        Bandwidth limit = Bandwidth.classic(Objects.requireNonNullElse(parameterApiRequestCallLimit, 3),
                          Refill.greedy(Objects.requireNonNullElse(parameterApiRequestCallLimitTokens, 3),
                          Duration.ofMinutes(Objects.requireNonNullElse(parameterApiRequestCallLimitMinutes, 1))));

        this.bucket = Bucket.builder().addLimit(limit).build();
    }

    @ApiOperation(value = "Sum Two Numbers And Add External Percentage", tags = "Sum Two Numbers")
    @GetMapping(value = "sum-two-numbers")
    public ResponseEntity<ResponseDTO<SumTwoNumbersDTO>> sumTwoNumbersPlusExternalPercentage(HttpServletRequest request,
                                                                                             @ApiParam(value = "Number One", required = true, example = "123456789") @RequestParam(value = "numberOne") Double numberOne,
                                                                                             @ApiParam(value = "Number Two", required = true, example = "987654321") @RequestParam(value = "numberTwo") Double numberTwo) throws TenpoException {
        ResponseDTO<SumTwoNumbersDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage(messageSuccess);
        responseDTO.setStatus(HttpStatus.OK);

        if (Objects.requireNonNull(bucket.tryConsume(1))) {
            responseDTO.setData(iSumTwoNumbersMapper.ModelInDTO(iSumTwoNumbersPortIn.sumTwoNumbersPlusExternalPercentage(numberOne, numberTwo)));
            return new ResponseEntity<>(responseDTO, responseDTO.getStatus());
        }

        responseDTO.setMessage(messageProblemsWithRequestLimit);
        responseDTO.setStatus(HttpStatus.TOO_MANY_REQUESTS);
        return new ResponseEntity<>(responseDTO, responseDTO.getStatus());
    }

}
