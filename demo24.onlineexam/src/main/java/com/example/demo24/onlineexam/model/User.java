package com.example.demo24.onlineexam.model;

public class User {
    private final String userId;
    private final String name;
    private final boolean isAdmin;

    public User(String userId, String name, boolean isAdmin) {
        this.userId = userId;
        this.name = name;
        this.isAdmin = isAdmin;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public boolean isAdmin() { return isAdmin; }
}
