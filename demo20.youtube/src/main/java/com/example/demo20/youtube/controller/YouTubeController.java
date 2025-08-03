package com.example.demo20.youtube.controller;

import com.example.demo20.youtube.model.User;
import com.example.demo20.youtube.model.Video;
import com.example.demo20.youtube.service.YouTubeService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/youtube")
public class YouTubeController {

    private final YouTubeService service;

    public YouTubeController(YouTubeService service) {
        this.service = service;
    }

    @PostMapping("/user")
    public User registerUser(@RequestParam String id, @RequestParam String name) {
        return service.registerUser(id, name);
    }

    @PostMapping("/upload")
    public Video upload(@RequestParam String title,
                        @RequestParam String description,
                        @RequestParam String uploaderId,
                        @RequestParam String tags) {
        List<String> tagList = Arrays.asList(tags.split(","));
        return service.uploadVideo(title, description, uploaderId, tagList);
    }

    @GetMapping("/view/{id}")
    public Video viewVideo(@PathVariable String id) {
        return service.viewVideo(id);
    }

    @GetMapping("/trending")
    public List<Video> trending(@RequestParam(defaultValue = "5") int top) {
        return service.getTrendingVideos(top);
    }

    @GetMapping("/search")
    public List<Video> search(@RequestParam String keyword) {
        return service.search(keyword);
    }

    @GetMapping("/user/{userId}/videos")
    public List<Video> userVideos(@PathVariable String userId) {
        return service.getVideosByUser(userId);
    }

    @GetMapping("/video/{id}")
    public Optional<Video> getVideo(@PathVariable String id) {
        return service.getVideo(id);
    }
}
