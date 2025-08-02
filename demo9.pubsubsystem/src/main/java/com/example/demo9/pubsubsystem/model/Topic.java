package com.example.demo9.pubsubsystem.model;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Topic {
    private final String name;
    private final Queue<Message> messages = new ConcurrentLinkedQueue<>();
    private final List<Subscriber> subscribers = new ArrayList<>();

    public Topic(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public Queue<Message> getMessages() { return messages; }
    public List<Subscriber> getSubscribers() { return subscribers; }

    public void publish(Message message) {
        messages.offer(message);
    }

    public void addSubscriber(Subscriber sub) {
        subscribers.add(sub);
    }
}
