package com.example.demo20.youtube.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Video {
    private final String videoId;
    private final String title;
    private final String description;
    private final String uploaderId;
    private final List<String> tags;
    private int views;
    private final LocalDateTime uploadedAt;

    public Video(String title, String description, String uploaderId, List<String> tags) {
        this.videoId = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.uploaderId = uploaderId;
        this.tags = tags;
        this.views = 0;
        this.uploadedAt = LocalDateTime.now();
    }

    public String getVideoId() { return videoId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getUploaderId() { return uploaderId; }
    public List<String> getTags() { return tags; }
    public int getViews() { return views; }
    public void incrementViews() { views++; }
    public LocalDateTime getUploadedAt() { return uploadedAt; }
}
