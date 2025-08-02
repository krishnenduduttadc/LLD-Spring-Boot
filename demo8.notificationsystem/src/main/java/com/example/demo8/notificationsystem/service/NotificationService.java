package com.example.demo8.notificationsystem.service;

import com.example.demo8.notificationsystem.model.*;
import com.example.demo8.notificationsystem.sender.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationService {

    private final Map<ChannelType, NotificationSender> senderMap = new HashMap<>();
    private final Map<String, User> userDb = new HashMap<>(); // dummy in-memory store

    public NotificationService(EmailSender emailSender, SmsSender smsSender, PushSender pushSender,WhatsAppSender whatsAppSender) {
        senderMap.put(ChannelType.EMAIL, emailSender);
        senderMap.put(ChannelType.SMS, smsSender);
        senderMap.put(ChannelType.PUSH, pushSender);
        senderMap.put(ChannelType.WHATSAPP, whatsAppSender);
    }

    public void registerUser(User user) {
        userDb.put(user.getUserId(), user);
    }

    public void sendNotification(Notification notification) {
        User user = userDb.get(notification.getUserId());

        if (user == null) {
            throw new RuntimeException("User not found: " + notification.getUserId());
        }

        if (!user.getPreferredChannels().contains(notification.getChannelType())) {
            throw new RuntimeException("User does not allow " + notification.getChannelType());
        }

        NotificationSender sender = senderMap.get(notification.getChannelType());

        if (sender != null) {
            sender.send(user, notification);
        } else {
            throw new RuntimeException("Unsupported channel: " + notification.getChannelType());
        }
    }
}
