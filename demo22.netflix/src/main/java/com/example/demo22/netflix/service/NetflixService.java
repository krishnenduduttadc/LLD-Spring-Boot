package com.example.demo22.netflix.service;

import com.example.demo22.netflix.model.*;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class NetflixService {

    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Map<String, Video> videos = new ConcurrentHashMap<>();
    private final List<ViewRecord> views = new ArrayList<>();

    public User registerUser(String id, String name) {
        User user = new User(id, name);
        users.put(id, user);
        return user;
    }

    public Video uploadVideo(String title, String language, Genre genre, List<String> tags) {
        Video video = new Video(title, language, genre, tags);
        videos.put(video.getVideoId(), video);
        return video;
    }

    public void watchVideo(String userId, String videoId) {
        if (!users.containsKey(userId) || !videos.containsKey(videoId)) {
            throw new RuntimeException("Invalid user or video");
        }

        videos.get(videoId).incrementViews();
        views.add(new ViewRecord(userId, videoId, LocalDateTime.now()));
    }

    public List<Video> getAllVideos() {
        return new ArrayList<>(videos.values());
    }

    public List<Video> getTrendingVideos(int limit) {
        return videos.values().stream()
                .sorted(Comparator.comparingInt(Video::getViews).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<Video> getUserHistory(String userId) {
        Set<String> watchedIds = views.stream()
                .filter(v -> v.getUserId().equals(userId))
                .map(ViewRecord::getVideoId)
                .collect(Collectors.toSet());

        return watchedIds.stream()
                .map(videos::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<Video> search(String keyword) {
        return videos.values().stream()
                .filter(v -> v.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                             v.getTags().stream().anyMatch(tag -> tag.toLowerCase().contains(keyword.toLowerCase())))
                .collect(Collectors.toList());
    }

    public List<Video> filterByGenre(Genre genre) {
        return videos.values().stream()
                .filter(v -> v.getGenre() == genre)
                .collect(Collectors.toList());
    }
}
