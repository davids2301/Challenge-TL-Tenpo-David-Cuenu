package cl.com.tenpo.application.port.in;

import cl.com.tenpo.domain.configuration.annotations.PortIn;
import cl.com.tenpo.domain.configuration.exception.TenpoException;
import cl.com.tenpo.domain.model.SumTwoNumbersModel;

@PortIn
public interface ISumTwoNumbersPortIn
{
    /**
     * Service to make sum two numbers and add percent obtained from external service
     * @param numberOne, Number One To Sum
     * @author David Cuenu
     */
    SumTwoNumbersModel sumTwoNumbersPlusExternalPercentage(Double numberOne, Double numberTwo) throws TenpoException;
}