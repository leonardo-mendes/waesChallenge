package services;

import domains.Document;
import domains.enums.Side;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import repositories.DocumentRepository;
import services.contracts.DocumentServiceContract;
import services.exceptions.DocumentIncompleteException;
import services.exceptions.DocumentNotFoundException;

import java.util.Arrays;
import java.util.Optional;

@Service
public class DocumentService implements DocumentServiceContract {

    @Autowired
    public DocumentRepository documentRepository;

    private static final Logger LOG = LoggerFactory.getLogger(DocumentService.class);

    public String insert(Integer id, String data, Side side) {
        this.validateData(data);
        documentRepository.save(this.fillDocument(id, data, side));
        return "Document " + side.toString().toLowerCase() + "-side saved successfully.";
    }

    public String documentAnalysis(Integer id) {
        Optional<Document> documentSaved = documentRepository.findById(id);
        if (!documentSaved.isPresent()) {
            throw new DocumentNotFoundException("Document not found.");
        }
        if (StringUtils.isEmpty(documentSaved.get().getLeft()) || StringUtils.isEmpty(documentSaved.get().getRight())) {
            throw new DocumentIncompleteException("Document Base64 data missing.");
        }

        byte[] bytesLeft = documentSaved.get().getLeft().getBytes();
        byte[] bytesRight = documentSaved.get().getRight().getBytes();

        return this.findDifferences(bytesLeft, bytesRight);
    }

    private void validateData(String data) {
        if (data.isEmpty()) {
            LOG.warn("DocumentRequest is blank or null.");
            throw new DocumentIncompleteException("DocumentRequest is blank or null.");
        }
        if (!Base64.isBase64(data)){
            LOG.warn("DocumentRequest is invalid.");
            throw new DocumentIncompleteException("DocumentRequest is invalid.");
        }
    }

    private Document fillDocument(Integer id, String data, Side side) {
        Document document = new Document();
        Optional<Document> documentSaved = documentRepository.findById(id);

        if (documentSaved.isPresent()) {
            document.setId(id);
            document.setLeft(documentSaved.get().getLeft());
            document.setRight(documentSaved.get().getRight());
        } else {
            document.setId(id);
        }

        if (Side.LEFT.equals(side)) {
            document.setLeft(data);
        } else if (Side.RIGHT.equals(side)) {
            document.setRight(data);
        } else {
            LOG.warn("Invalid side sent.");
        }

        return document;
    }

    private String findDifferences(byte[] bytesLeft, byte[] bytesRight) {
        boolean blnResult = Arrays.equals(bytesLeft, bytesRight);
        String offsets = "";

        if (blnResult) {
            return "Document Base64 data are equal.";
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

        return "Document Base64 data got the same size, but their offsets are different:" + offsets + ".";
    }

}
