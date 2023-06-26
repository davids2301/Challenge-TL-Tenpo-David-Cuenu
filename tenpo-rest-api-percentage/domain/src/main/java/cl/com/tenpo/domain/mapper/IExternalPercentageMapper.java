package cl.com.tenpo.domain.mapper;

import cl.com.tenpo.domain.dto.ExternalPercentageDTO;
import cl.com.tenpo.domain.model.ExternalPercentageModel;

public interface IExternalPercentageMapper {

    ExternalPercentageDTO ModelInDTO(ExternalPercentageModel model);

    ExternalPercentageModel DTOInModel(ExternalPercentageDTO dto);

}

