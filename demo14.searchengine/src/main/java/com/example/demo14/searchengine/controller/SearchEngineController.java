package com.example.demo14.searchengine.controller;

import com.example.demo14.searchengine.model.Document;
import com.example.demo14.searchengine.service.SearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchEngineController {

    private final SearchService searchService;

    public SearchEngineController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("/add")
    public String addDocument(@RequestBody Document doc) {
        searchService.addDocument(doc.getId(), doc.getTitle(), doc.getContent());
        return "Document added";
    }

    @GetMapping
    public List<Document> search(@RequestParam String query) {
        return searchService.search(query);
    }
}
