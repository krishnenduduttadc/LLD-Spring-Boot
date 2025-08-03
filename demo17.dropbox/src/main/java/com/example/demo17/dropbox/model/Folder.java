package com.example.demo17.dropbox.model;

import java.util.*;

public class Folder {
    private final String id;
    private final String name;
    private final String ownerId;
    private final Folder parent;
    private final List<Folder> subfolders = new ArrayList<>();
    private final List<FileEntity> files = new ArrayList<>();

    public Folder(String id, String name, String ownerId, Folder parent) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.parent = parent;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getOwnerId() { return ownerId; }
    public Folder getParent() { return parent; }
    public List<Folder> getSubfolders() { return subfolders; }
    public List<FileEntity> getFiles() { return files; }
}
