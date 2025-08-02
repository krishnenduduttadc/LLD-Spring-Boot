package com.example.demo8.notificationsystem.model;

public class Notification {
    private String notificationId;
    private String userId;
    private String message;
    private ChannelType channelType;

    public Notification(String notificationId, String userId, String message, ChannelType channelType) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.message = message;
        this.channelType = channelType;
    }

    public String getNotificationId() { return notificationId; }
    public String getUserId() { return userId; }
    public String getMessage() { return message; }
    public ChannelType getChannelType() { return channelType; }
}
