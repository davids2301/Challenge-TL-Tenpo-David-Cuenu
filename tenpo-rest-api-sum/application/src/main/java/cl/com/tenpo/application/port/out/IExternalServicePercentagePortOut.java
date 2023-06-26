package cl.com.tenpo.application.port.out;

import cl.com.tenpo.domain.configuration.annotations.PortOut;
import cl.com.tenpo.domain.configuration.exception.TenpoException;
import cl.com.tenpo.domain.dto.ExternalPercentageDTO;

@PortOut
public interface IExternalServicePercentagePortOut
{
    ExternalPercentageDTO sumTwoNumbersPlusExternalPercentage(Double numberOne, Double numberTwo) throws TenpoException;
}