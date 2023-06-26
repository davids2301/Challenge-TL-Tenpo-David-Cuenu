package cl.com.tenpo.domain.mapper.impl;

import cl.com.tenpo.domain.dto.CallsHistoryDTO;
import cl.com.tenpo.domain.entity.CallsHistoryEntity;
import cl.com.tenpo.domain.mapper.ICallsHistoryMapper;
import cl.com.tenpo.domain.model.CallsHistoryModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

@Service
public class CallsHistoryMapperImpl implements ICallsHistoryMapper {
    @Override
    public CallsHistoryDTO EntityInDTO(CallsHistoryEntity entity) {
        return CallsHistoryDTO.builder().id(entity.getId()).request(entity.getRequest()).response(entity.getResponse()).dateAndHour(entity.getDateAndHour()).build();
    }

    @Override
    public CallsHistoryEntity DTOInEntity(CallsHistoryDTO dto) {
        return CallsHistoryEntity.builder().id(dto.getId()).request(dto.getRequest()).response(dto.getResponse()).dateAndHour(dto.getDateAndHour()).build();
    }

    @Override
    public CallsHistoryDTO ModelInDTO(CallsHistoryModel model) {
        return CallsHistoryDTO.builder().id(model.getId()).request(model.getRequest()).response(model.getResponse()).dateAndHour(model.getDateAndHour()).build();
    }

    @Override
    public CallsHistoryModel ModelInDTO(CallsHistoryDTO dto) {
        return CallsHistoryModel.builder().id(dto.getId()).request(dto.getRequest()).response(dto.getResponse()).dateAndHour(dto.getDateAndHour()).build();
    }

    @Override
    public List<CallsHistoryDTO> ListEntityInDTOList(List<CallsHistoryEntity> list) {
        return list.stream().map(e -> EntityInDTO(e)).collect(toCollection(ArrayList::new));
    }

    @Override
    public List<CallsHistoryEntity> ListDTOInEntityList(List<CallsHistoryDTO> list) {
        return list.stream().map(e -> DTOInEntity(e)).collect(toCollection(ArrayList::new));
    }

    @Override
    public List<CallsHistoryDTO> ListModelInDTOList(List<CallsHistoryModel> list) {
        return list.stream().map(e -> ModelInDTO(e)).collect(toCollection(ArrayList::new));
    }

    @Override
    public List<CallsHistoryModel> ListDTOInModelList(List<CallsHistoryDTO> list) {
        return list.stream().map(e -> ModelInDTO(e)).collect(toCollection(ArrayList::new));
    }
}
