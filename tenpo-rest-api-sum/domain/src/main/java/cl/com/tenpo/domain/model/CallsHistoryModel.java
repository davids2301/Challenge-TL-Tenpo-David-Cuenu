package cl.com.tenpo.domain.model;

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
public class CallsHistoryModel implements Serializable {
    private Integer id;

    private String request;

    private String response;

    private LocalDateTime dateAndHour;
}