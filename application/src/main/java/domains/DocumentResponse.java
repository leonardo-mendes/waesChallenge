package domains;

public class DocumentResponse {

    private String message;

    public DocumentResponse() { }

    public DocumentResponse(String message) {
        this.message = message;
    }

    public String getData() {
        return message;
    }

    public void setData(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"");
        sb.append("message");
        sb.append("\":");
        sb.append("\"");
        sb.append(message);
        sb.append("\"");
        sb.append("}");
        return sb.toString();
    }
}
