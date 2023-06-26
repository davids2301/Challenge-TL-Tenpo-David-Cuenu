package cl.com.tenpo.domain.mapper;

import cl.com.tenpo.domain.dto.SumTwoNumbersDTO;
import cl.com.tenpo.domain.model.SumTwoNumbersModel;

public interface ISumTwoNumbersMapper {

    SumTwoNumbersDTO ModelInDTO(SumTwoNumbersModel model);

    SumTwoNumbersModel DTOInModel(SumTwoNumbersDTO dto);

}