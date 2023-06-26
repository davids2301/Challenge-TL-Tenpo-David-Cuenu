package cl.com.tenpo.domain.model;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SumTwoNumbersModel {
    private Double numberOne;
    private Double numberTwo;
    private Double percentageApplied;
    private Double sumValues;
}
