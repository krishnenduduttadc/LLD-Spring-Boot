package com.example.demo12.instagramfeed.model;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String userId;
    private String name;
    private Set<String> following = new HashSet<>();

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public Set<String> getFollowing() { return following; }

    public void follow(String targetUserId) {
        following.add(targetUserId);
    }

    public void unfollow(String targetUserId) {
        following.remove(targetUserId);
    }
}
