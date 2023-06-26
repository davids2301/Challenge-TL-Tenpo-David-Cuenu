package cl.com.tenpo.domain.mapper;

import cl.com.tenpo.domain.dto.ExternalPercentageDTO;
import cl.com.tenpo.domain.entity.ExternalPercentageEntity;

public interface IExternalPercentageMapper {

    ExternalPercentageDTO EntityInDTO(ExternalPercentageEntity model);

    ExternalPercentageEntity DTOInEntity(ExternalPercentageDTO dto);

}