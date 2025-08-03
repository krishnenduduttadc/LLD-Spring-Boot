package com.example.demo17.dropbox.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FileEntity {
    private final String id;
    private final String name;
    private final String ownerId;
    private final Folder folder;
    private final List<Version> versions = new ArrayList<>();

    public FileEntity(String id, String name, String ownerId, Folder folder) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.folder = folder;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getOwnerId() { return ownerId; }
    public Folder getFolder() { return folder; }
    public List<Version> getVersions() { return versions; }

    public void addVersion(String content) {
        versions.add(new Version(content, LocalDateTime.now()));
    }

    public Version getLatestVersion() {
        if (versions.isEmpty()) return null;
        return versions.get(versions.size() - 1);
    }

    public static class Version {
        private final String content;
        private final LocalDateTime uploadedAt;

        public Version(String content, LocalDateTime uploadedAt) {
            this.content = content;
            this.uploadedAt = uploadedAt;
        }

        public String getContent() { return content; }
        public LocalDateTime getUploadedAt() { return uploadedAt; }
    }
}
