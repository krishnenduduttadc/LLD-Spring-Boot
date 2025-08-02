package com.example.demo8.notificationsystem.sender;

import com.example.demo8.notificationsystem.model.Notification;
import com.example.demo8.notificationsystem.model.User;
import org.springframework.stereotype.Component;

@Component
public class EmailSender implements NotificationSender {

    @Override
    public void send(User user, Notification notification) {
        System.out.println("📧 Sending EMAIL to " + user.getEmail() + ": " + notification.getMessage());
    }
}
