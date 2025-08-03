package com.example.demo22.netflix.model;

import java.util.List;
import java.util.UUID;

public class Video {
    private final String videoId;
    private final String title;
    private final String language;
    private final Genre genre;
    private final List<String> tags;
    private int views;

    public Video(String title, String language, Genre genre, List<String> tags) {
        this.videoId = UUID.randomUUID().toString();
        this.title = title;
        this.language = language;
        this.genre = genre;
        this.tags = tags;
        this.views = 0;
    }

    public String getVideoId() { return videoId; }
    public String getTitle() { return title; }
    public String getLanguage() { return language; }
    public Genre getGenre() { return genre; }
    public List<String> getTags() { return tags; }
    public int getViews() { return views; }
    public void incrementViews() { views++; }
}
