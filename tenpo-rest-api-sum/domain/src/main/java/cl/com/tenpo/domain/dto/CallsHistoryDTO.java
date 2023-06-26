package cl.com.tenpo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class CallsHistoryDTO implements Serializable {
    private Integer id;

    private String request;

    private String response;

    private LocalDateTime dateAndHour;
}