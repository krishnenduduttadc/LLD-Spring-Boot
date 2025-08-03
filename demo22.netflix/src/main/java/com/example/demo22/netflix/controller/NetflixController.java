package com.example.demo22.netflix.controller;

import com.example.demo22.netflix.model.*;
import com.example.demo22.netflix.service.NetflixService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/netflix")
public class NetflixController {

    private final NetflixService service;

    public NetflixController(NetflixService service) {
        this.service = service;
    }

    @PostMapping("/user")
    public User registerUser(@RequestParam String id, @RequestParam String name) {
        return service.registerUser(id, name);
    }

    @PostMapping("/upload")
    public Video upload(@RequestParam String title,
                        @RequestParam String language,
                        @RequestParam Genre genre,
                        @RequestParam String tags) {
        List<String> tagList = Arrays.asList(tags.split(","));
        return service.uploadVideo(title, language, genre, tagList);
    }

    @PostMapping("/watch")
    public void watch(@RequestParam String userId, @RequestParam String videoId) {
        service.watchVideo(userId, videoId);
    }

    @GetMapping("/all")
    public List<Video> allVideos() {
        return service.getAllVideos();
    }

    @GetMapping("/trending")
    public List<Video> trending(@RequestParam(defaultValue = "5") int limit) {
        return service.getTrendingVideos(limit);
    }

    @GetMapping("/user/{userId}/history")
    public List<Video> history(@PathVariable String userId) {
        return service.getUserHistory(userId);
    }

    @GetMapping("/search")
    public List<Video> search(@RequestParam String keyword) {
        return service.search(keyword);
    }

    @GetMapping("/genre")
    public List<Video> filterByGenre(@RequestParam Genre genre) {
        return service.filterByGenre(genre);
    }
}
