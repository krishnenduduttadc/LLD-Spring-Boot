package com.example.demo15.chat.model;

import java.time.LocalDateTime;

public class Message {
    private final String messageId;
    private final String senderId;
    private final String receiverId;
    private final String content;
    private final LocalDateTime timestamp;

    public Message(String messageId, String senderId, String receiverId, String content, LocalDateTime timestamp) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getMessageId() { return messageId; }
    public String getSenderId() { return senderId; }
    public String getReceiverId() { return receiverId; }
    public String getContent() { return content; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
