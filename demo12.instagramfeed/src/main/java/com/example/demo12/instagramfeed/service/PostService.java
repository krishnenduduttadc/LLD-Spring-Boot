package com.example.demo12.instagramfeed.service;

import com.example.demo12.instagramfeed.model.Post;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final List<Post> posts = new ArrayList<>();

    public Post createPost(String userId, String content) {
        Post post = new Post(UUID.randomUUID().toString(), userId, content, LocalDateTime.now());
        posts.add(post);
        return post;
    }

    public List<Post> getPostsByUser(String userId) {
        return posts.stream()
                .filter(p -> p.getUserId().equals(userId))
                .sorted(Comparator.comparing(Post::getTimestamp).reversed())
                .collect(Collectors.toList());
    }

    public List<Post> getAllPosts() {
        return posts;
    }
}
