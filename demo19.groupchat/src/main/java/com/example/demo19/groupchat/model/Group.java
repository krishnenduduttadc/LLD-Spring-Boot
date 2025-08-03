package com.example.demo19.groupchat.model;

import java.util.HashSet;
import java.util.Set;

public class Group {
    private final String groupId;
    private final String name;
    private final Set<String> memberIds = new HashSet<>();

    public Group(String groupId, String name) {
        this.groupId = groupId;
        this.name = name;
    }

    public String getGroupId() { return groupId; }
    public String getName() { return name; }
    public Set<String> getMemberIds() { return memberIds; }

    public void addMember(String userId) {
        memberIds.add(userId);
    }

    public void removeMember(String userId) {
        memberIds.remove(userId);
    }
}
