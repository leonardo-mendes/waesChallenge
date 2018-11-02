package services;

import domains.Document;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

    public Document teste(Document document){
        return new Document(1, "", "sim");
    }
}
