package cl.com.tenpo.domain.mapper.impl;

import cl.com.tenpo.domain.dto.ExternalPercentageDTO;
import cl.com.tenpo.domain.entity.ExternalPercentageEntity;
import cl.com.tenpo.domain.mapper.IExternalPercentageMapper;
import org.springframework.stereotype.Service;

@Service
public class ExternalPercentageMapperImpl implements IExternalPercentageMapper {
    @Override
    public ExternalPercentageDTO EntityInDTO(ExternalPercentageEntity model) {
        return ExternalPercentageDTO.builder().percentageApplied(model.getPercentageApplied()).calculatedValue(model.getCalculatedValue()).numberTwo(model.getNumberTwo()).numberOne(model.getNumberOne()).dateTime(model.getDateTime()).build();
    }

    @Override
    public ExternalPercentageEntity DTOInEntity(ExternalPercentageDTO dto) {
        return ExternalPercentageEntity.builder().percentageApplied(dto.getPercentageApplied()).calculatedValue(dto.getCalculatedValue()).numberTwo(dto.getNumberTwo()).numberOne(dto.getNumberOne()).dateTime(dto.getDateTime()).build();
    }
}
