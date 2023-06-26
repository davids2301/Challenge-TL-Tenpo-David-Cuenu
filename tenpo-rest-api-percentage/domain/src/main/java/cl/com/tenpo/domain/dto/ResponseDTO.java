package cl.com.tenpo.domain.dto;


import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ResponseDTO<T> {
    private HttpStatus status;
    private String message;
    private T data;
}
