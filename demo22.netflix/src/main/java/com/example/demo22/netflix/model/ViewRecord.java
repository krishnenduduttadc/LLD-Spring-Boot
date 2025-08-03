package com.example.demo22.netflix.model;

import java.time.LocalDateTime;

public class ViewRecord {
    private final String userId;
    private final String videoId;
    private final LocalDateTime watchedAt;

    public ViewRecord(String userId, String videoId, LocalDateTime watchedAt) {
        this.userId = userId;
        this.videoId = videoId;
        this.watchedAt = watchedAt;
    }

    public String getUserId() { return userId; }
    public String getVideoId() { return videoId; }
    public LocalDateTime getWatchedAt() { return watchedAt; }
}
