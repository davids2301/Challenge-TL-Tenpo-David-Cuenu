package cl.com.tenpo.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@Builder
public class ErrorDTO {

    private HttpStatus status;
    private String message;
    private List<?> errors;

}