package services.exceptions;

public class DocumentNotFoundException extends RuntimeException {

    public DocumentNotFoundException(String exception) {
        super(exception);
    }

    public DocumentNotFoundException(String exception, Throwable cause) {
        super(exception, cause);
    }

}
