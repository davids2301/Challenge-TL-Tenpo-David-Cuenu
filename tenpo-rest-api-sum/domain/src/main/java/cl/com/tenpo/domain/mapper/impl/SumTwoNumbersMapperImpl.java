package cl.com.tenpo.domain.mapper.impl;

import cl.com.tenpo.domain.dto.SumTwoNumbersDTO;
import cl.com.tenpo.domain.mapper.ISumTwoNumbersMapper;
import cl.com.tenpo.domain.model.SumTwoNumbersModel;
import org.springframework.stereotype.Service;

@Service
public class SumTwoNumbersMapperImpl implements ISumTwoNumbersMapper {
    @Override
    public SumTwoNumbersDTO ModelInDTO(SumTwoNumbersModel model) {
        return SumTwoNumbersDTO.builder().numberOne(model.getNumberOne()).numberTwo(model.getNumberTwo()).percentageApplied(model.getPercentageApplied()).sumValues(model.getSumValues()).build();
    }

    @Override
    public SumTwoNumbersModel DTOInModel(SumTwoNumbersDTO dto) {
        return SumTwoNumbersModel.builder().numberOne(dto.getNumberOne()).numberTwo(dto.getNumberTwo()).percentageApplied(dto.getPercentageApplied()).sumValues(dto.getSumValues()).build();
    }
}
