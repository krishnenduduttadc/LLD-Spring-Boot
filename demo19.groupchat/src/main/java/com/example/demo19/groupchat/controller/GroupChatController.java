package com.example.demo19.groupchat.controller;

import com.example.demo19.groupchat.model.Group;
import com.example.demo19.groupchat.model.GroupMessage;
import com.example.demo19.groupchat.model.User;
import com.example.demo19.groupchat.service.GroupChatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groupchat")
public class GroupChatController {

    private final GroupChatService service;

    public GroupChatController(GroupChatService service) {
        this.service = service;
    }

    @PostMapping("/user")
    public User registerUser(@RequestParam String userId, @RequestParam String name) {
        return service.registerUser(userId, name);
    }

    @PostMapping("/group")
    public Group createGroup(@RequestParam String groupId, @RequestParam String name) {
        return service.createGroup(groupId, name);
    }

    @PostMapping("/group/{groupId}/add")
    public void addUserToGroup(@PathVariable String groupId, @RequestParam String userId) {
        service.addUserToGroup(groupId, userId);
    }

    @PostMapping("/group/{groupId}/send")
    public GroupMessage sendMessage(@PathVariable String groupId,
                                    @RequestParam String senderId,
                                    @RequestParam String content) {
        return service.sendMessage(senderId, groupId, content);
    }

    @GetMapping("/group/{groupId}/messages")
    public List<GroupMessage> getMessages(@PathVariable String groupId) {
        return service.getGroupMessages(groupId);
    }
}
