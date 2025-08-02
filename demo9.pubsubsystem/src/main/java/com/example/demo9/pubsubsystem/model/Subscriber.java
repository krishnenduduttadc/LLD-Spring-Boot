package com.example.demo9.pubsubsystem.model;

public interface Subscriber {
    void consume(String topicName, Message message);
    String getId();
}
