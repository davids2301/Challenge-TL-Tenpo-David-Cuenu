package cl.com.tenpo.percentage.domain.configuration.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TenpoException extends Exception {

    private final Class<?> clase;

    public <T> TenpoException(Class<?> clase, Exception ex) {
        super(ex.getMessage());
        this.clase = clase;
    }

    public TenpoException(Class<?> clase, String message) {
        super(message);
        this.clase = clase;
    }
}
