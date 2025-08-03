package com.example.demo15.chat.controller;

import com.example.demo15.chat.model.Message;
import com.example.demo15.chat.model.User;
import com.example.demo15.chat.service.ChatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestParam String userId, @RequestParam String name) {
        return chatService.registerUser(userId, name);
    }

    @PostMapping("/send")
    public Message sendMessage(@RequestParam String senderId,
                               @RequestParam String receiverId,
                               @RequestParam String content) {
        return chatService.sendMessage(senderId, receiverId, content);
    }

    @GetMapping("/messages")
    public List<Message> getMessages(@RequestParam String user1, @RequestParam String user2) {
        return chatService.getMessages(user1, user2);
    }
}
