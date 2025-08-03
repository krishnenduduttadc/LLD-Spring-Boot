package com.example.demo23.googledocs.model;

import java.util.*;

public class Document {
    private final String docId;
    private String title;
    private String content;
    private final Map<String, AccessType> collaborators; // userId → access

    public Document(String docId, String title, String content, String ownerId) {
        this.docId = docId;
        this.title = title;
        this.content = content;
        this.collaborators = new HashMap<>();
        this.collaborators.put(ownerId, AccessType.OWNER);
    }

    public String getDocId() { return docId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Map<String, AccessType> getCollaborators() { return collaborators; }
}
