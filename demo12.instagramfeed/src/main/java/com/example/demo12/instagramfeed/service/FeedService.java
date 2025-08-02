package com.example.demo12.instagramfeed.service;

import com.example.demo12.instagramfeed.model.Post;
import com.example.demo12.instagramfeed.model.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FeedService {

    private final UserService userService;
    private final PostService postService;

    public FeedService(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    public List<Post> getFeed(String userId, int limit, int offset) {
        User user = userService.getUser(userId);
        if (user == null) return Collections.emptyList();

        Set<String> followees = user.getFollowing();

        List<Post> feedPosts = postService.getAllPosts().stream()
                .filter(p -> followees.contains(p.getUserId()))
                .sorted(Comparator.comparing(Post::getTimestamp).reversed())
                .skip(offset)
                .limit(limit)
                .collect(Collectors.toList());

        return feedPosts;
    }
}
