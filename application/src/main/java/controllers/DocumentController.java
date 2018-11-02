package controllers;

import domains.DocumentRequest;
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
    private String left(@PathVariable Integer id, @RequestBody DocumentRequest request) throws Exception {
        LOG.trace("Entering left(id={}, data={})", id, request);

        LOG.debug("Setting '{}' side of the document with the value: '{}' Left", request);
        documentService.insert(id, request.getData(), LEFT_SIDE);
        LOG.info("'{}' side of the document saved successfuly for id: '{}'", LEFT_SIDE, id);

        String message = buildJsonResponse("Document left-side saved successfuly");
        LOG.trace("Leaving left(id, data)={}", message);
        return message;
    }

    private String buildJsonResponse(String message) {
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