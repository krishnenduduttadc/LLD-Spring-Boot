package com.example.demo9.pubsubsystem.broker;

import com.example.demo9.pubsubsystem.model.*;

import java.util.*;
import java.util.concurrent.*;

public class MessageBroker {

    private final Map<String, Topic> topics = new ConcurrentHashMap<>();
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public void createTopic(String topicName) {
        topics.putIfAbsent(topicName, new Topic(topicName));
    }

    public void subscribe(String topicName, Subscriber subscriber) {
        Topic topic = topics.get(topicName);
        if (topic != null) {
            topic.addSubscriber(subscriber);
        }
    }

    public void publish(String topicName, Message message) {
        Topic topic = topics.get(topicName);
        if (topic == null) {
            throw new RuntimeException("Topic not found: " + topicName);
        }

        topic.publish(message);

        // Fan-out delivery (async)
        for (Subscriber sub : topic.getSubscribers()) {
            executor.submit(() -> sub.consume(topicName, message));
        }
    }

    public Set<String> getTopics() {
        return topics.keySet();
    }

    public void shutdown() {
        executor.shutdown();
    }
}
