package com.example.demo19.groupchat.service;

import com.example.demo19.groupchat.model.Group;
import com.example.demo19.groupchat.model.GroupMessage;
import com.example.demo19.groupchat.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GroupChatService {

    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Map<String, Group> groups = new ConcurrentHashMap<>();
    private final Map<String, List<GroupMessage>> groupMessages = new ConcurrentHashMap<>();

    public User registerUser(String userId, String name) {
        User user = new User(userId, name);
        users.put(userId, user);
        return user;
    }

    public Group createGroup(String groupId, String groupName) {
        Group group = new Group(groupId, groupName);
        groups.put(groupId, group);
        return group;
    }

    public void addUserToGroup(String groupId, String userId) {
        Group group = groups.get(groupId);
        if (group == null) throw new RuntimeException("Group not found");
        if (!users.containsKey(userId)) throw new RuntimeException("User not found");
        group.addMember(userId);
    }

    public GroupMessage sendMessage(String senderId, String groupId, String content) {
        Group group = groups.get(groupId);
        if (group == null || !group.getMemberIds().contains(senderId)) {
            throw new RuntimeException("Sender is not in group");
        }

        GroupMessage msg = new GroupMessage(
                UUID.randomUUID().toString(),
                senderId,
                groupId,
                content,
                LocalDateTime.now()
        );

        groupMessages.computeIfAbsent(groupId, k -> new ArrayList<>()).add(msg);
        return msg;
    }

    public List<GroupMessage> getGroupMessages(String groupId) {
        return groupMessages.getOrDefault(groupId, Collections.emptyList());
    }
}
