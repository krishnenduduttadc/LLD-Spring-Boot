package com.example.demo12.instagramfeed.controller;

import com.example.demo12.instagramfeed.model.*;
import com.example.demo12.instagramfeed.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FeedController {

    private final UserService userService;
    private final PostService postService;
    private final FeedService feedService;

    public FeedController(UserService userService, PostService postService, FeedService feedService) {
        this.userService = userService;
        this.postService = postService;
        this.feedService = feedService;
    }

    @PostMapping("/user/create")
    public User createUser(@RequestParam String userId, @RequestParam String name) {
        return userService.createUser(userId, name);
    }

    @PostMapping("/post/create")
    public Post createPost(@RequestParam String userId, @RequestParam String content) {
        return postService.createPost(userId, content);
    }

    @PostMapping("/follow")
    public void follow(@RequestParam String followerId, @RequestParam String followeeId) {
        userService.follow(followerId, followeeId);
    }

    @PostMapping("/unfollow")
    public void unfollow(@RequestParam String followerId, @RequestParam String followeeId) {
        userService.unfollow(followerId, followeeId);
    }

    @GetMapping("/feed")
    public List<Post> getFeed(
            @RequestParam String userId,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "0") int offset
    ) {
        return feedService.getFeed(userId, limit, offset);
    }
}
