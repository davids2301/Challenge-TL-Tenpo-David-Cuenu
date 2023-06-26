package cl.com.tenpo.percentage.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ExternalPercentageDTO {
    private Double numberOne;

    private Double numberTwo;

    private Double percentageApplied;

    private Double calculatedValue;

    private LocalDateTime dateTime;
}
