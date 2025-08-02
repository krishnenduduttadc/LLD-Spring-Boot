package com.example.demo12.instagramfeed.model;

import java.time.LocalDateTime;

public class Post {
    private String postId;
    private String userId;
    private String content;
    private LocalDateTime timestamp;

    public Post(String postId, String userId, String content, LocalDateTime timestamp) {
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getPostId() { return postId; }
    public String getUserId() { return userId; }
    public String getContent() { return content; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
