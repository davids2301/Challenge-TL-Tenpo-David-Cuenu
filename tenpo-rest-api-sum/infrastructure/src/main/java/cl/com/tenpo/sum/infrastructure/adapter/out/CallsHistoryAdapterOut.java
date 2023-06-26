package cl.com.tenpo.sum.infrastructure.adapter.out;

import cl.com.tenpo.application.port.out.ICallsHistoryPortOut;
import cl.com.tenpo.domain.configuration.annotations.AdapterOut;
import cl.com.tenpo.domain.configuration.exception.TenpoException;
import cl.com.tenpo.domain.dto.CallsHistoryDTO;
import cl.com.tenpo.domain.entity.CallsHistoryEntity;
import cl.com.tenpo.domain.mapper.ICallsHistoryMapper;
import cl.com.tenpo.domain.repository.ICallsHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AdapterOut
public class CallsHistoryAdapterOut implements ICallsHistoryPortOut {

    @Autowired
    private ICallsHistoryRepository iCallsHistoryRepository;

    @Autowired
    private ICallsHistoryMapper iCallsHistoryMapper;

    @Override
    public List<CallsHistoryDTO> callsHistoryPaginated(Integer pageNumber, Integer numberOfRecords, Boolean descendingOrder, String sortBy) throws TenpoException {
        Pageable pageable = PageRequest.of(pageNumber, numberOfRecords, ((descendingOrder) ? Sort.Direction.DESC : Sort.Direction.ASC), sortBy);
        Page<CallsHistoryEntity> callsHistoryEntities=iCallsHistoryRepository.findAll(pageable);
        List<CallsHistoryDTO> list=iCallsHistoryMapper.ListEntityInDTOList(callsHistoryEntities.toList());
        return list;
    }
}
