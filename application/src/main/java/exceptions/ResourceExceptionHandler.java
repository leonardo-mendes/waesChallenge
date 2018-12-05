package exceptions;

import domains.ErrorDetailResponse;
import org.springframework.http.ResponseEntity;
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
        return ResponseEntity.ok(new ErrorDetailResponse(new Date(), e.getMessage(), request.getDescription(false)));
    }

    @ExceptionHandler(DocumentIncompleteException.class)
    public ResponseEntity<ErrorDetailResponse> documentNotFound(DocumentIncompleteException e, WebRequest request) {
        return ResponseEntity.ok(new ErrorDetailResponse(new Date(), e.getMessage(), request.getDescription(false)));
    }

}
