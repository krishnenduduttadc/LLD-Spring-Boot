package com.example.demo8.notificationsystem.controller;

import com.example.demo8.notificationsystem.model.*;
import com.example.demo8.notificationsystem.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;

        // Register dummy user for demo
        User u = new User(
                "u1",
                "Ramesh",
                "ramesh@mail.com",
                "+919000000001",
                List.of(ChannelType.EMAIL, ChannelType.SMS, ChannelType.WHATSAPP)
        );
        notificationService.registerUser(u);
    }

    @PostMapping("/send")
    public String sendNotification(@RequestBody Notification notification) {
        notificationService.sendNotification(notification);
        return "Notification sent!";
    }

    @PostMapping("/send/dynamic")
    public String sendDynamicNotification(
            @RequestParam String userId,
            @RequestParam String message,
            @RequestParam ChannelType channel
    ) {
        Notification n = new Notification(UUID.randomUUID().toString(), userId, message, channel);
        notificationService.sendNotification(n);
        return "Sent via " + channel;
    }
}
