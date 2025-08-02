package com.example.demo8.notificationsystem.model;

import java.util.List;

public class User {
    private String userId;
    private String name;
    private String email;
    private String phone;
    private List<ChannelType> preferredChannels;

    public User(String userId, String name, String email, String phone, List<ChannelType> preferredChannels) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.preferredChannels = preferredChannels;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public List<ChannelType> getPreferredChannels() { return preferredChannels; }
}
