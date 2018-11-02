package domains;

public class DocumentRequest {

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
