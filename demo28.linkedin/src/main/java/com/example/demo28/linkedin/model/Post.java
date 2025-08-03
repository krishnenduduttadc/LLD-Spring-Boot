package com.example.demo28.linkedin.model;

import java.time.LocalDateTime;

public class Post {
    private final String postId;
    private final String userId;
    private final String content;
    private final LocalDateTime timestamp;

    public Post(String postId, String userId, String content) {
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    public String getPostId() { return postId; }
    public String getUserId() { return userId; }
    public String getContent() { return content; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
