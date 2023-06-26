package cl.com.tenpo.domain.configuration.exception;

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
