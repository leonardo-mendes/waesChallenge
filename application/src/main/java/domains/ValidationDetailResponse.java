package domains;

import domains.exceptions.FieldMessage;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ValidationDetailResponse{
    private static final long serialVersionUID = 1L;

    private String timestamp;
    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationDetailResponse(Date timestamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        this.timestamp = simpleDateFormat.format(timestamp);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }

}