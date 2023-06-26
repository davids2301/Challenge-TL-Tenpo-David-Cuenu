package cl.com.tenpo.aplication.usecase;

import cl.com.tenpo.aplication.port.in.IExternalPercentagePortIn;
import cl.com.tenpo.domain.configuration.annotations.MultiUseCase;
import cl.com.tenpo.domain.configuration.exceptions.TenpoException;
import cl.com.tenpo.domain.model.ExternalPercentageModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@MultiUseCase
@Service
class ExternalPercentageUseCase implements IExternalPercentagePortIn {

    @Value(value = "${parameter.external.percentage.value}")
    private Double parameterExternalPercentageValue;

    @Override
    public ExternalPercentageModel sumTwoNumbersAndAndReturnValueApplyingPercentageParametrized(Double numberOne, Double numberTwo) throws TenpoException {
        // Keep here business logic it
        Double calculatedValue=(((numberOne+numberTwo)*parameterExternalPercentageValue)/100);
        return ExternalPercentageModel.builder().numberOne(numberOne).numberTwo(numberTwo).percentageApplied(parameterExternalPercentageValue).dateTime(LocalDateTime.now()).calculatedValue(calculatedValue).build();
    }
}
