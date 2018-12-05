package services.contracts;

import domains.enums.Side;

public interface DocumentServiceContract {

    public String insert(Integer id, String data, Side side);

    public String documentAnalysis(Integer id);

}
