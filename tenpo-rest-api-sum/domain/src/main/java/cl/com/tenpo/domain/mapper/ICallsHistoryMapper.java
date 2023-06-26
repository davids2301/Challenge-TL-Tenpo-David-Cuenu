package cl.com.tenpo.domain.mapper;

import cl.com.tenpo.domain.dto.CallsHistoryDTO;
import cl.com.tenpo.domain.entity.CallsHistoryEntity;
import cl.com.tenpo.domain.model.CallsHistoryModel;

import java.util.List;

public interface ICallsHistoryMapper {

    CallsHistoryDTO EntityInDTO(CallsHistoryEntity entity);

    CallsHistoryEntity DTOInEntity(CallsHistoryDTO dto);

    CallsHistoryDTO ModelInDTO(CallsHistoryModel model);

    CallsHistoryModel ModelInDTO(CallsHistoryDTO dto);

    List<CallsHistoryDTO> ListEntityInDTOList(List<CallsHistoryEntity> list);

    List<CallsHistoryEntity> ListDTOInEntityList(List<CallsHistoryDTO> list);

    List<CallsHistoryDTO> ListModelInDTOList(List<CallsHistoryModel> list);

    List<CallsHistoryModel> ListDTOInModelList(List<CallsHistoryDTO> list);

}