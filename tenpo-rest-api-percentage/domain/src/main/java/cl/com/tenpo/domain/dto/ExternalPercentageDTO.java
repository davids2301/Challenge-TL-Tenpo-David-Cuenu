package cl.com.tenpo.domain.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExternalPercentageDTO {
    private Double numberOne;

    private Double numberTwo;

    private Double percentageApplied;

    private Double calculatedValue;

    private LocalDateTime dateTime;
}
