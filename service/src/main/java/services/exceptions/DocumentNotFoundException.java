package services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DocumentNotFoundException extends RuntimeException {

    public DocumentNotFoundException(String exception) {
        super(exception);
    }

    public DocumentNotFoundException(String exception, Throwable cause) {
        super(exception, cause);
    }

}
