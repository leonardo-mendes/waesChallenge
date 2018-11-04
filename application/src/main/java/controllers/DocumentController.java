package controllers;

import domains.DocumentRequest;
import domains.DocumentResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.DocumentService;

@RestController
@RequestMapping("/v1/diff")
@ApiOperation(value = "Document API - This API provides all services for the application.")
public class DocumentController {

    private final String LEFT_SIDE = "left";
    private final String RIGHT_SIDE = "right";

    @Autowired
    private DocumentService documentService;

    private static final Logger LOG = LoggerFactory.getLogger(DocumentController.class);

    @ApiOperation(value = "This endpoint provide a compare the both documents sides.")
    @GetMapping(value = "/{id}/result")
    private DocumentResponse analysis(@PathVariable Integer id) {
        return new DocumentResponse(documentService.documentAnalysis(id))  ;
    }

    @ApiOperation(value = "This endpoint provides a resource to fill the left side of the document.")
    @PostMapping(value = "/{id}/left")
    private DocumentResponse left(@PathVariable Integer id, @RequestBody DocumentRequest request) throws Exception {
        return insertDocument(id, request, LEFT_SIDE);
    }

    @ApiOperation(value = "This endpoint provides a resource to fill the right side of the document.")
    @PostMapping(value = "/{id}/right")
    private DocumentResponse right(@PathVariable Integer id, @RequestBody DocumentRequest request) throws Exception {
        return insertDocument(id, request, RIGHT_SIDE);
    }

    private DocumentResponse insertDocument(Integer id, DocumentRequest request, String side) throws Exception {
        LOG.info("Entering " + side + " (id={}, data={})", id, request);

        String message = documentService.insert(id, request.getData(), side);
        LOG.info("'{}' side of the document saved successfuly for id: '{}'", side, id);

        return new DocumentResponse(message);
    }

}