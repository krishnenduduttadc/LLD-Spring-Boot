package com.example.demo27.githubsystem.model;

import java.time.LocalDateTime;

public class Commit {
    private final String commitId;
    private final String message;
    private final String authorId;
    private final LocalDateTime timestamp;

    public Commit(String commitId, String message, String authorId) {
        this.commitId = commitId;
        this.message = message;
        this.authorId = authorId;
        this.timestamp = LocalDateTime.now();
    }

    public String getCommitId() { return commitId; }
    public String getMessage() { return message; }
    public String getAuthorId() { return authorId; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
