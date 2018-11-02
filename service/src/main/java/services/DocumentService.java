package services;

import domains.Document;
import domains.enums.Side;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.DocumentRepository;

import javax.xml.bind.ValidationException;
import java.util.Optional;

@Service
public class DocumentService {

    @Autowired
    public DocumentRepository documentRepository;

    private static final Logger LOG = LoggerFactory.getLogger(DocumentService.class);

    public Document insert(Integer id, String data, String side) throws Exception {
        Document document = null;

        if (validate(id, data)) {
            Optional<Document> documentSaved = documentRepository.findById(id);

            if (!documentSaved.isPresent()) {
                document = new Document();
                document.setId(id);
            }

            if (Side.LEFT.toString().equalsIgnoreCase(side)) {
                document.setLeft(data);
            } else if (Side.RIGHT.toString().equalsIgnoreCase(side)) {
                document.setRight(data);
            } else {
                LOG.warn("Invalid side sent.");
            }
            document = documentRepository.save(document);
        }
        return document;
    }

    public boolean validate(Integer id, String data) throws ValidationException {
        boolean isValid = true;
        LOG.trace("Entering validate(id={}, data={})", id, data);

        LOG.debug("Validating request for id '{}'", id);
        if (data.isEmpty()) {
            LOG.warn("Json data is blank or null");
            isValid = false;
        }
        LOG.trace("Leaving validate(id, data)={}", isValid);
        return isValid;
    }

}

