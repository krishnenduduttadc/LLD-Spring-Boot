package com.example.demo9.pubsubsystem.subscribers;

import com.example.demo9.pubsubsystem.model.*;

public class PrintSubscriber implements Subscriber {

    private final String id;

    public PrintSubscriber(String id) {
        this.id = id;
    }

    @Override
    public void consume(String topicName, Message message) {
        System.out.println("[Subscriber " + id + "] received from '" + topicName + "': " + message.getContent());
    }

    @Override
    public String getId() {
        return id;
    }
}
