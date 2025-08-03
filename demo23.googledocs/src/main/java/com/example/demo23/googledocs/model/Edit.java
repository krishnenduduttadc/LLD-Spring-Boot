package com.example.demo23.googledocs.model;

import java.time.LocalDateTime;

public class Edit {
    private final String userId;
    private final String docId;
    private final String content;
    private final LocalDateTime timestamp;

    public Edit(String userId, String docId, String content) {
        this.userId = userId;
        this.docId = docId;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    public String getUserId() { return userId; }
    public String getDocId() { return docId; }
    public String getContent() { return content; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
