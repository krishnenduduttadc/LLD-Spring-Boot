package com.example.demo15.chat.service;

import com.example.demo15.chat.model.Message;
import com.example.demo15.chat.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatService {

    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Map<String, List<Message>> chatHistory = new ConcurrentHashMap<>();

    public User registerUser(String userId, String name) {
        User user = new User(userId, name);
        users.put(userId, user);
        return user;
    }

    public Message sendMessage(String senderId, String receiverId, String content) {
        if (!users.containsKey(senderId) || !users.containsKey(receiverId)) {
            throw new IllegalArgumentException("User not found");
        }

        Message message = new Message(
                UUID.randomUUID().toString(),
                senderId,
                receiverId,
                content,
                LocalDateTime.now()
        );

        String chatKey = getChatKey(senderId, receiverId);
        chatHistory.computeIfAbsent(chatKey, k -> new ArrayList<>()).add(message);

        return message;
    }

    public List<Message> getMessages(String user1, String user2) {
        String chatKey = getChatKey(user1, user2);
        return chatHistory.getOrDefault(chatKey, Collections.emptyList());
    }

    private String getChatKey(String u1, String u2) {
        return (u1.compareTo(u2) < 0) ? u1 + "_" + u2 : u2 + "_" + u1;
    }
}
