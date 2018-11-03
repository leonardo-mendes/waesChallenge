package services;

import domains.Document;
import domains.enums.Side;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import repositories.DocumentRepository;

import javax.xml.bind.ValidationException;
import java.util.Arrays;
import java.util.Optional;

@Service
public class DocumentService {

    @Autowired
    public DocumentRepository documentRepository;

    private static final Logger LOG = LoggerFactory.getLogger(DocumentService.class);

    public Document insert(Integer id, String data, String side) throws Exception {
        Document document = new Document();

        if (this.validate(id, data)) {
            Optional<Document> documentSaved = documentRepository.findById(id);
            if (documentSaved.isPresent()) {
                document.setId(id);
                document.setLeft(documentSaved.get().getLeft());
                document.setRight(documentSaved.get().getRight());
            } else {
                document.setId(id);
            }

            fillDocument(document, data, side);
            document = documentRepository.save(document);
            LOG.info("Document saved!");
        }
        return document;
    }

    public String documentAnalysis(Integer id) {
        Optional<Document> documentSaved = documentRepository.findById(id);
        if (!documentSaved.isPresent()) {
            return "Document not found.";
        }

        if (StringUtils.isEmpty(documentSaved.get().getLeft()) || StringUtils.isEmpty(documentSaved.get().getRight())) {
            return "Document Base64 data missing";
        }

        byte[] bytesLeft = documentSaved.get().getLeft().getBytes();
        byte[] bytesRight = documentSaved.get().getRight().getBytes();

        return findDifferences(bytesLeft, bytesRight);
    }

    private boolean validate(Integer id, String data) throws ValidationException {
        boolean isValid = true;

        LOG.debug("Validating request for id '{}'", id);
        if (data.isEmpty()) {
            LOG.warn("DocumentRequest is blank or null");
            isValid = false;
        }
        return isValid;
    }

    private void fillDocument(Document document, String data, String side) {
        if (Side.LEFT.toString().equalsIgnoreCase(side)) {
            document.setLeft(data);
        } else if (Side.RIGHT.toString().equalsIgnoreCase(side)) {
            document.setRight(data);
        } else {
            LOG.warn("Invalid side sent.");
        }
    }

    private String findDifferences(byte[] bytesLeft, byte[] bytesRight) {
        boolean blnResult = Arrays.equals(bytesLeft, bytesRight);
        String offsets = "";

        if (blnResult) {
            return "Document Base64 data are equal";
        } else if (bytesLeft.length != bytesRight.length) {
            return "Document Base64 data have not same size.";
        } else {
            byte different = 0;
            for (int index = 0; index < bytesLeft.length; index++) {
                different = (byte) (bytesLeft[index] ^ bytesRight[index]);
                if (different != 0) {
                    offsets = offsets + " " + index;
                }
            }
        }

        return "Document Base64 data got the same size, but their offsets are different:" + offsets;
    }

}

