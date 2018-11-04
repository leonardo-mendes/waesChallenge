package domains;

public class DocumentResponse {

    private String data;

    public DocumentResponse() { }

    public DocumentResponse(String message) {
        this.data = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String message) {
        this.data = message;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"");
        sb.append("data");
        sb.append("\":");
        sb.append("\"");
        sb.append(data);
        sb.append("\"");
        sb.append("}");
        return sb.toString();
    }
}
