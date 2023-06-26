package cl.com.tenpo.domain.dto;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SumTwoNumbersDTO {
    private Double numberOne;
    private Double numberTwo;
    private Double percentageApplied;
    private Double sumValues;
}
