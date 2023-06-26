package cl.com.tenpo.domain.entity;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("ExternalPercentage")
public class ExternalPercentageEntity implements Serializable {

    private String id;

    private Double numberOne;

    private Double numberTwo;

    private Double percentageApplied;

    private Double calculatedValue;

    private LocalDateTime dateTime;
}
