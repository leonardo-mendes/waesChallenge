package domains;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ErrorDetailResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String timestamp;
    private String message;
    private String details;

    public ErrorDetailResponse(Date timestamp, String message, String details) {
        super();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        this.timestamp = simpleDateFormat.format(timestamp);
        this.message = message;
        this.details = details;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
