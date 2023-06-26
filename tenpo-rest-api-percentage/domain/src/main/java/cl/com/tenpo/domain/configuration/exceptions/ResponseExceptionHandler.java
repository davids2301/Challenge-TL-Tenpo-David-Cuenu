package cl.com.tenpo.domain.configuration.exceptions;

import cl.com.tenpo.domain.dto.ErrorDTO;
import feign.FeignException;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private Environment environment;

    private final List<String> errors;

    ResponseExceptionHandler() {
        this.errors = new ArrayList<>();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Map<String, String>> listaErrores = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            String defaultMessage = error.getDefaultMessage();
            String propiedad = defaultMessage == null ? "" : environment.getProperty(defaultMessage);
            Map<String, String> campos = new HashMap<>();
            campos.put("error", error.getField() + " No encontrado en el request");
            campos.put("campo", error.getField());
            campos.put("mensaje", propiedad);
            listaErrores.add(campos);
        }
        ErrorDTO errorDTO = ErrorDTO.builder().status(HttpStatus.BAD_REQUEST).message("Error Durante la Validación del Objeto " + ex.getObjectName()).errors(listaErrores).build();
        return handleExceptionInternal(ex, errorDTO, headers, errorDTO.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        errors.add(ex.getParameterName() + " No fue encontrado en el request");
        ErrorDTO errorDTO = ErrorDTO.builder().status(HttpStatus.BAD_REQUEST).message(ex.getLocalizedMessage()).errors(errors).build();
        return new ResponseEntity<>(errorDTO, new HttpHeaders(), errorDTO.getStatus());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> constraintViolationException(ConstraintViolationException ex, WebRequest request) {
        for (ConstraintViolation<?> violation : ex.getConstraintViolations())
            errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": " + violation.getMessage());
        ErrorDTO errorDTO = ErrorDTO.builder().status(HttpStatus.BAD_REQUEST).message(ex.getLocalizedMessage()).errors(errors).build();
        return new ResponseEntity<>(errorDTO, new HttpHeaders(), errorDTO.getStatus());
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {
        Class<?> requiredType = ex.getRequiredType();
        String name = requiredType == null ? "" : requiredType.getName();
        errors.add(ex.getName() + " debería ser de tipo " + name);
        ErrorDTO errorDTO = ErrorDTO.builder().status(HttpStatus.BAD_REQUEST).message(ex.getLocalizedMessage()).errors(errors).build();
        return new ResponseEntity<>(errorDTO, new HttpHeaders(), errorDTO.getStatus());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> exception(Exception ex) {
        ErrorDTO errorDTO = ErrorDTO.builder().status(HttpStatus.BAD_REQUEST).message(ex.getLocalizedMessage()).errors(errors).build();
        LogManager.getLogger(ResponseExceptionHandler.class).error(ex);
        return new ResponseEntity<>(errorDTO, new HttpHeaders(), errorDTO.getStatus());
    }

    @ExceptionHandler({TenpoException.class})
    public ResponseEntity<Object> integracionException(TenpoException ex) {
        ErrorDTO errorDTO = ErrorDTO.builder().status(HttpStatus.BAD_REQUEST).message(ex.getLocalizedMessage()).errors(errors).build();
        LogManager.getLogger(ex.getClase()).error(ex);
        return new ResponseEntity<>(errorDTO, new HttpHeaders(), errorDTO.getStatus());
    }

    @ExceptionHandler({MaxUploadSizeExceededException.class})
    public ResponseEntity<Object> maxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
        ErrorDTO errorDTO = ErrorDTO.builder().status(HttpStatus.BAD_REQUEST).message(environment.getProperty("mensaje.onbase.pesomaximoarchivo.error")+ environment.getProperty("spring.servlet.multipart.max-file-size")).errors(errors).build();
        return new ResponseEntity<>(errorDTO, new HttpHeaders(), errorDTO.getStatus());
    }

    @ExceptionHandler(MultipartException.class)
    void handleMultipartException(MultipartException ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(),"Please select a file");
    }

    @ExceptionHandler({FeignException.class})
    public ResponseEntity<Object> exception(FeignException ex) {
        ErrorDTO errorDTO = ErrorDTO.builder().status(HttpStatus.BAD_REQUEST).message("Servicio externo no disponible").errors(errors).build();
        LogManager.getLogger(ResponseExceptionHandler.class).error(ex);
        return new ResponseEntity<>(errorDTO, new HttpHeaders(), errorDTO.getStatus());
    }
}
