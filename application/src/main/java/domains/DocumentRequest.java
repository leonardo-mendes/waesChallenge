package domains;

import javax.validation.constraints.NotEmpty;

public class DocumentRequest {

    @NotEmpty(message="This field is mandatory.")
    private String data;

    public DocumentRequest() { }

    public DocumentRequest(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
