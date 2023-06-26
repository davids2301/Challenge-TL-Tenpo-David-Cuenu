package cl.com.tenpo.domain.client;

import cl.com.tenpo.domain.configuration.exception.TenpoException;
import cl.com.tenpo.domain.dto.ExternalPercentageDTO;
import cl.com.tenpo.domain.dto.ResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="ExternalPercentageClient", url="${parameter.endpoint.parameterized.percentage}")
public interface IExternalServicePercentageClient {

    @ApiOperation(value = "This api is designed for make that number one make sum with number two and after applying the n% to sum then return number calculated to caller to them can end operation", tags = "External Percentage")
    @GetMapping(value = "sum-two-numbers-and-return-value-applying-percentage-parametrized")
    public ResponseEntity<ResponseDTO<ExternalPercentageDTO>> sumTwoNumbersAndAndReturnValueApplyingPercentageParametrized(@ApiParam(value = "Number One", required = true, example = "123456789") @RequestParam(value = "numberOne") Double numberOne,
                                                                                                                           @ApiParam(value = "Number Two", required = true, example = "987654321") @RequestParam(value = "numberTwo") Double numberTwo) throws TenpoException;

}
