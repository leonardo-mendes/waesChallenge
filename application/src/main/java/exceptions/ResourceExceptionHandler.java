package exceptions;

import domains.ErrorDetailResponse;
import domains.ValidationDetailResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import services.exceptions.DocumentIncompleteException;
import services.exceptions.DocumentNotFoundException;

import java.util.Date;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DocumentNotFoundException.class)
    public ResponseEntity<ErrorDetailResponse> documentNotFound(DocumentNotFoundException e, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDetailResponse(new Date(), e.getMessage(), request.getDescription(false)));
    }

    @ExceptionHandler(DocumentIncompleteException.class)
    public ResponseEntity<ErrorDetailResponse> documentNotFound(DocumentIncompleteException e, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDetailResponse(new Date(), e.getMessage(), request.getDescription(false)));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ValidationDetailResponse error = new ValidationDetailResponse(new Date());
        e.getBindingResult().getFieldErrors().forEach(p -> error.addError(p.getField(), p.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
