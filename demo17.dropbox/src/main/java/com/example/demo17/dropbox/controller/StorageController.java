package com.example.demo17.dropbox.controller;

import com.example.demo17.dropbox.model.FileEntity;
import com.example.demo17.dropbox.model.Folder;
import com.example.demo17.dropbox.model.User;
import com.example.demo17.dropbox.service.StorageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dropbox")
public class StorageController {

    private final StorageService service;

    public StorageController(StorageService service) {
        this.service = service;
    }

    @PostMapping("/user")
    public User createUser(@RequestParam String id, @RequestParam String name) {
        return service.createUser(id, name);
    }

    @PostMapping("/folder")
    public Folder createFolder(@RequestParam String name,
                               @RequestParam String ownerId,
                               @RequestParam(required = false) String parentId) {
        return service.createFolder(name, ownerId, parentId);
    }

    @PostMapping("/file")
    public FileEntity uploadFile(@RequestParam String folderId,
                                 @RequestParam String fileName,
                                 @RequestParam String ownerId,
                                 @RequestParam String content) {
        return service.uploadFile(folderId, fileName, ownerId, content);
    }

    @GetMapping("/folder/{id}/files")
    public List<FileEntity> listFiles(@PathVariable String id) {
        return service.listFiles(id);
    }

    @GetMapping("/file/{id}/latest")
    public FileEntity.Version getLatestVersion(@PathVariable String id) {
        return service.getLatestVersion(id);
    }
}
