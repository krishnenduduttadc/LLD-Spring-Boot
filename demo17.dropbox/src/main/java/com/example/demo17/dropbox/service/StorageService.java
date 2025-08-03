package com.example.demo17.dropbox.service;

import com.example.demo17.dropbox.model.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {

    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Folder> folders = new HashMap<>();
    private final Map<String, FileEntity> files = new HashMap<>();

    public User createUser(String id, String name) {
        User user = new User(id, name);
        users.put(id, user);
        return user;
    }

    public Folder createFolder(String name, String ownerId, String parentFolderId) {
        Folder parent = folders.get(parentFolderId);
        String id = UUID.randomUUID().toString();
        Folder folder = new Folder(id, name, ownerId, parent);
        folders.put(id, folder);
        if (parent != null) {
            parent.getSubfolders().add(folder);
        }
        return folder;
    }

    public FileEntity uploadFile(String folderId, String fileName, String ownerId, String content) {
        Folder folder = folders.get(folderId);
        String id = UUID.randomUUID().toString();
        FileEntity file = new FileEntity(id, fileName, ownerId, folder);
        file.addVersion(content);
        folder.getFiles().add(file);
        files.put(id, file);
        return file;
    }

    public List<FileEntity> listFiles(String folderId) {
        Folder folder = folders.get(folderId);
        return folder.getFiles();
    }

    public FileEntity.Version getLatestVersion(String fileId) {
        FileEntity file = files.get(fileId);
        return file.getLatestVersion();
    }

    public Folder getFolder(String folderId) {
        return folders.get(folderId);
    }
}
