package cl.com.tenpo.application.usecase;

import cl.com.tenpo.application.port.in.ICallsHistoryPortIn;
import cl.com.tenpo.application.port.out.ICallsHistoryPortOut;
import cl.com.tenpo.domain.configuration.annotations.MultiUseCase;
import cl.com.tenpo.domain.configuration.exception.TenpoException;
import cl.com.tenpo.domain.mapper.ICallsHistoryMapper;
import cl.com.tenpo.domain.model.CallsHistoryModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@MultiUseCase
@Service
class CallsHistoryUseCase implements ICallsHistoryPortIn {

    Logger logger= LoggerFactory.getLogger(CallsHistoryUseCase.class);

    @Autowired
    private ICallsHistoryPortOut iCallsHistoryPortOut;

    @Autowired
    private ICallsHistoryMapper iCallsHistoryMapper;


    @Override
    public List<CallsHistoryModel> callsHistoryPaginated(Integer pageNumber, Integer numberOfRecords, Boolean descendingOrder, String sortBy) throws TenpoException {
        // Keep here business logic it

        logger.warn("We get calls history paginated list from db");
        List<CallsHistoryModel>  callsHistoryModels= iCallsHistoryMapper.ListDTOInModelList(iCallsHistoryPortOut.callsHistoryPaginated(pageNumber, numberOfRecords, descendingOrder, sortBy));

        return callsHistoryModels;
    }
}