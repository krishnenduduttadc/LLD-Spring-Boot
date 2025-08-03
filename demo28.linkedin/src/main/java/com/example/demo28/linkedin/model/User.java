package com.example.demo28.linkedin.model;

import java.util.HashSet;
import java.util.Set;

public class User {
    private final String userId;
    private String name;
    private String headline;
    private Set<String> connections = new HashSet<>();

    public User(String userId, String name, String headline) {
        this.userId = userId;
        this.name = name;
        this.headline = headline;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getHeadline() { return headline; }
    public Set<String> getConnections() { return connections; }

    public void connect(String otherId) { connections.add(otherId); }
    public void disconnect(String otherId) { connections.remove(otherId); }

    public void setHeadline(String headline) { this.headline = headline; }
    public void setName(String name) { this.name = name; }
}
