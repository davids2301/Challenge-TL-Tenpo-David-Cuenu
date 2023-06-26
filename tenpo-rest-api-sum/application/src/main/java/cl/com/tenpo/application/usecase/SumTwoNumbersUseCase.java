package cl.com.tenpo.application.usecase;

import cl.com.tenpo.application.port.in.ISumTwoNumbersPortIn;
import cl.com.tenpo.application.port.out.IExternalServicePercentagePortOut;
import cl.com.tenpo.domain.configuration.annotations.MultiUseCase;
import cl.com.tenpo.domain.configuration.exception.TenpoException;
import cl.com.tenpo.domain.dto.ExternalPercentageDTO;
import cl.com.tenpo.domain.model.SumTwoNumbersModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@MultiUseCase
@Service
class SumTwoNumbersUseCase implements ISumTwoNumbersPortIn {

    Logger logger= LoggerFactory.getLogger(SumTwoNumbersUseCase.class);

    @Autowired
    private IExternalServicePercentagePortOut iExternalServicePercentagePortOut;

    @Override
    public SumTwoNumbersModel sumTwoNumbersPlusExternalPercentage(Double numberOne, Double numberTwo) throws TenpoException {
        // Keep here business logic it
        SumTwoNumbersModel sumTwoNumbersModel=new SumTwoNumbersModel();
        sumTwoNumbersModel.setNumberOne(numberOne);
        sumTwoNumbersModel.setNumberTwo(numberTwo);

        logger.warn("We get information about percentage and value to sum with two others values number one and number two respectively");
        ExternalPercentageDTO externalPercentageDTO=iExternalServicePercentagePortOut.sumTwoNumbersPlusExternalPercentage(numberOne, numberTwo);

        logger.warn("We change the percentage value through which we calculate third value to sum");
        sumTwoNumbersModel.setPercentageApplied(externalPercentageDTO.getPercentageApplied());

        sumTwoNumbersModel.setSumValues(sumTwoNumbersModel.getNumberOne()+sumTwoNumbersModel.getNumberTwo()+externalPercentageDTO.getCalculatedValue());

        return sumTwoNumbersModel;
    }
}
