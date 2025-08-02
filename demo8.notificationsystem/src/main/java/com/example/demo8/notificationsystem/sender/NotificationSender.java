package com.example.demo8.notificationsystem.sender;

import com.example.demo8.notificationsystem.model.Notification;
import com.example.demo8.notificationsystem.model.User;

public interface NotificationSender {
    void send(User user, Notification notification);
}
