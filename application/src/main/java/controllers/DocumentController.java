package controllers;

import domains.DocumentRequest;
import domains.DocumentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.DocumentService;

@RestController
@RequestMapping("/v1/diff/{id}")
public class DocumentController {

    private final String LEFT_SIDE = "left";
    private final String RIGHT_SIDE = "right";

    @Autowired
    private DocumentService documentService;

    private static final Logger LOG = LoggerFactory.getLogger(DocumentController.class);

    @PostMapping(value = "/left")
    private DocumentResponse left(@PathVariable Integer id, @RequestBody DocumentRequest request) throws Exception {
        return insertDocument(id, request, LEFT_SIDE);
    }

    @PostMapping(value = "/right")
    private DocumentResponse right(@PathVariable Integer id, @RequestBody DocumentRequest request) throws Exception {
        return insertDocument(id, request, RIGHT_SIDE);
    }

    private DocumentResponse insertDocument(Integer id, DocumentRequest request, String side) throws Exception {
        LOG.trace("Entering " + side + " (id={}, data={})", id, request);

        LOG.debug("Setting '{}' side of the document with the value: '{}' Left", request);
        documentService.insert(id, request.getData(), side);
        LOG.info("'{}' side of the document saved successfuly for id: '{}'", side, id);

        return new DocumentResponse("Document " + side + "-side saved successfuly");
    }

}