package com.example.demo12.instagramfeed.service;

import com.example.demo12.instagramfeed.model.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private final Map<String, User> users = new HashMap<>();

    public User createUser(String userId, String name) {
        User user = new User(userId, name);
        users.put(userId, user);
        return user;
    }

    public User getUser(String userId) {
        return users.get(userId);
    }

    public void follow(String followerId, String followeeId) {
        User follower = users.get(followerId);
        if (follower != null && users.containsKey(followeeId)) {
            follower.follow(followeeId);
        }
    }

    public void unfollow(String followerId, String followeeId) {
        User follower = users.get(followerId);
        if (follower != null) {
            follower.unfollow(followeeId);
        }
    }
}
