package cl.com.tenpo.aplication.port.in;

import cl.com.tenpo.domain.configuration.annotations.PortIn;
import cl.com.tenpo.domain.configuration.exceptions.TenpoException;
import cl.com.tenpo.domain.model.ExternalPercentageModel;

@PortIn
public interface IExternalPercentagePortIn
{
    /**
     * Service to make sum two numbers and add percent obtained from external service
     * @param numberOne, Number One To Sum
     * @author David Cuenu
     */
    ExternalPercentageModel sumTwoNumbersAndAndReturnValueApplyingPercentageParametrized(Double numberOne, Double numberTwo) throws TenpoException;
}
