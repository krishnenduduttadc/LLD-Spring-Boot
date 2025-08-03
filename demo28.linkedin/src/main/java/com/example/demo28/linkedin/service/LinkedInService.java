package com.example.demo28.linkedin.service;

import com.example.demo28.linkedin.model.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class LinkedInService {

    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Map<String, Post> posts = new ConcurrentHashMap<>();
    private final Map<String, Job> jobs = new ConcurrentHashMap<>();
    private final Map<String, Application> applications = new ConcurrentHashMap<>();

    // Users
    public User createUser(String id, String name, String headline) {
        User user = new User(id, name, headline);
        users.put(id, user);
        return user;
    }

    public void connectUsers(String user1, String user2) {
        users.get(user1).connect(user2);
        users.get(user2).connect(user1);
    }

    public void disconnectUsers(String user1, String user2) {
        users.get(user1).disconnect(user2);
        users.get(user2).disconnect(user1);
    }

    public List<User> getConnections(String userId) {
        return users.get(userId).getConnections().stream()
                .map(users::get).collect(Collectors.toList());
    }

    // Posts
    public Post createPost(String postId, String userId, String content) {
        Post post = new Post(postId, userId, content);
        posts.put(postId, post);
        return post;
    }

    public List<Post> getFeed(String userId) {
        Set<String> network = users.get(userId).getConnections();
        return posts.values().stream()
                .filter(p -> network.contains(p.getUserId()) || p.getUserId().equals(userId))
                .sorted(Comparator.comparing(Post::getTimestamp).reversed())
                .collect(Collectors.toList());
    }

    // Jobs
    public Job postJob(String jobId, String title, String company, String userId) {
        Job job = new Job(jobId, title, company, userId);
        jobs.put(jobId, job);
        return job;
    }

    public Application applyToJob(String appId, String jobId, String applicantId) {
        Application application = new Application(appId, jobId, applicantId);
        applications.put(appId, application);
        return application;
    }

    public List<Job> getAllJobs() {
        return new ArrayList<>(jobs.values());
    }
}
