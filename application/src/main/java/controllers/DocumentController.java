package controllers;

import domains.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.DocumentService;

import java.util.List;

@RestController
@RequestMapping("/v1/diff/{id}")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping
    public Document test(@RequestBody Document document){
        return  documentService.teste(document);
    }

    @GetMapping
    public List<Document> test1(){
        return  documentService.teste1();
    }

}