package services.exceptions;

public class DocumentIncompleteException extends RuntimeException {

    public DocumentIncompleteException(String exception) {
        super(exception);
    }

    public DocumentIncompleteException(String exception, Throwable cause) {
        super(exception, cause);
    }

}
