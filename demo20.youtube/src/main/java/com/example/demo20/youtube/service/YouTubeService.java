package com.example.demo20.youtube.service;

import com.example.demo20.youtube.model.User;
import com.example.demo20.youtube.model.Video;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class YouTubeService {

    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Map<String, Video> videos = new ConcurrentHashMap<>();

    public User registerUser(String id, String name) {
        User user = new User(id, name);
        users.put(id, user);
        return user;
    }

    public Video uploadVideo(String title, String desc, String uploaderId, List<String> tags) {
        if (!users.containsKey(uploaderId)) throw new RuntimeException("Uploader not found");

        Video video = new Video(title, desc, uploaderId, tags);
        videos.put(video.getVideoId(), video);
        return video;
    }

    public Video viewVideo(String videoId) {
        Video video = videos.get(videoId);
        if (video != null) video.incrementViews();
        return video;
    }

    public List<Video> getTrendingVideos(int topN) {
        return videos.values().stream()
                .sorted((v1, v2) -> Integer.compare(v2.getViews(), v1.getViews()))
                .limit(topN)
                .collect(Collectors.toList());
    }

    public List<Video> getVideosByUser(String userId) {
        return videos.values().stream()
                .filter(v -> v.getUploaderId().equals(userId))
                .collect(Collectors.toList());
    }

    public List<Video> search(String keyword) {
        return videos.values().stream()
                .filter(v -> v.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                             v.getTags().stream().anyMatch(tag -> tag.toLowerCase().contains(keyword.toLowerCase())))
                .collect(Collectors.toList());
    }

    public Optional<Video> getVideo(String id) {
        return Optional.ofNullable(videos.get(id));
    }
}
