package cl.com.tenpo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ExternalPercentageModel {
    private Double numberOne;
    
    private Double numberTwo;

    private Double percentageApplied;
    
    private Double calculatedValue;

    private LocalDateTime dateTime;
}
