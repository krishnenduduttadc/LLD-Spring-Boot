package com.example.demo19.groupchat.model;

import java.time.LocalDateTime;

public class GroupMessage {
    private final String messageId;
    private final String senderId;
    private final String groupId;
    private final String content;
    private final LocalDateTime timestamp;

    public GroupMessage(String messageId, String senderId, String groupId, String content, LocalDateTime timestamp) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.groupId = groupId;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getMessageId() { return messageId; }
    public String getSenderId() { return senderId; }
    public String getGroupId() { return groupId; }
    public String getContent() { return content; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
