package services.contracts;

public interface DocumentServiceContract {

    public String insert(Integer id, String data, String side);

    public String documentAnalysis(Integer id);

}
