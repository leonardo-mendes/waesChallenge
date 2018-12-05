package services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DocumentIncompleteException extends RuntimeException {

    public DocumentIncompleteException(String exception) {
        super(exception);
    }

    public DocumentIncompleteException(String exception, Throwable cause) {
        super(exception, cause);
    }

}
