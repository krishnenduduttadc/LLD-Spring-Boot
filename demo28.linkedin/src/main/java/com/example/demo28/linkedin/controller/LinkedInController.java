package com.example.demo28.linkedin.controller;

import com.example.demo28.linkedin.model.*;
import com.example.demo28.linkedin.service.LinkedInService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/linkedin")
public class LinkedInController {

    private final LinkedInService service;

    public LinkedInController(LinkedInService service) {
        this.service = service;
    }

    // User
    @PostMapping("/user")
    public User createUser(@RequestParam String id,
                           @RequestParam String name,
                           @RequestParam String headline) {
        return service.createUser(id, name, headline);
    }

    @PostMapping("/connect")
    public void connect(@RequestParam String user1, @RequestParam String user2) {
        service.connectUsers(user1, user2);
    }

    @PostMapping("/disconnect")
    public void disconnect(@RequestParam String user1, @RequestParam String user2) {
        service.disconnectUsers(user1, user2);
    }

    @GetMapping("/connections")
    public List<User> getConnections(@RequestParam String userId) {
        return service.getConnections(userId);
    }

    // Post
    @PostMapping("/post")
    public Post post(@RequestParam String postId,
                     @RequestParam String userId,
                     @RequestParam String content) {
        return service.createPost(postId, userId, content);
    }

    @GetMapping("/feed")
    public List<Post> feed(@RequestParam String userId) {
        return service.getFeed(userId);
    }

    // Job
    @PostMapping("/job")
    public Job postJob(@RequestParam String jobId,
                       @RequestParam String title,
                       @RequestParam String company,
                       @RequestParam String userId) {
        return service.postJob(jobId, title, company, userId);
    }

    @PostMapping("/apply")
    public Application apply(@RequestParam String appId,
                             @RequestParam String jobId,
                             @RequestParam String userId) {
        return service.applyToJob(appId, jobId, userId);
    }

    @GetMapping("/jobs")
    public List<Job> getJobs() {
        return service.getAllJobs();
    }
}
