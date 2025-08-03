package com.example.demo23.googledocs.controller;

import com.example.demo23.googledocs.model.*;
import com.example.demo23.googledocs.service.GoogleDocsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/docs")
public class GoogleDocsController {

    private final GoogleDocsService service;

    public GoogleDocsController(GoogleDocsService service) {
        this.service = service;
    }

    @PostMapping("/user")
    public User createUser(@RequestParam String id, @RequestParam String name) {
        return service.createUser(id, name);
    }

    @PostMapping("/create")
    public Document createDoc(@RequestParam String docId,
                              @RequestParam String title,
                              @RequestParam String content,
                              @RequestParam String ownerId) {
        return service.createDocument(docId, title, content, ownerId);
    }

    @PostMapping("/edit")
    public Document edit(@RequestParam String docId,
                         @RequestParam String userId,
                         @RequestParam String content) {
        return service.editDocument(docId, userId, content);
    }

    @PostMapping("/grant")
    public void grant(@RequestParam String docId,
                      @RequestParam String userId,
                      @RequestParam AccessType access) {
        service.grantAccess(docId, userId, access);
    }

    @GetMapping("/history/{docId}")
    public List<Edit> getHistory(@PathVariable String docId) {
        return service.getEditHistory(docId);
    }

    @GetMapping("/get")
    public Document get(@RequestParam String docId, @RequestParam String userId) {
        return service.getDocument(docId, userId);
    }
}
