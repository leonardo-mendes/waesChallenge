package services;

import domains.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.DocumentRepository;

import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public Document teste(Document document){
        return documentRepository.save(document);
    }

    public List<Document> teste1(){
        return documentRepository.findAll();
    }
}
