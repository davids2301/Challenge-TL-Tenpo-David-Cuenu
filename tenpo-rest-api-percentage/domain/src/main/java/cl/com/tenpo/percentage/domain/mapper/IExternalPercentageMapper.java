package cl.com.tenpo.percentage.domain.mapper;

import cl.com.tenpo.percentage.domain.dto.ExternalPercentageDTO;
import cl.com.tenpo.percentage.domain.model.ExternalPercentageModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IExternalPercentageMapper {

    ExternalPercentageDTO ModelInDTO(ExternalPercentageModel model);

    ExternalPercentageModel DTOInModel(ExternalPercentageDTO dto);

}

