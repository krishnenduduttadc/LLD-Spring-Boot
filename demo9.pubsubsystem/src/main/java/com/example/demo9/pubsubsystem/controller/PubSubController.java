package com.example.demo9.pubsubsystem.controller;

import com.example.demo9.pubsubsystem.broker.MessageBroker;
import com.example.demo9.pubsubsystem.model.Message;
import com.example.demo9.pubsubsystem.subscribers.PrintSubscriber;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PubSubController {

    private final MessageBroker broker = new MessageBroker();

    public PubSubController() {
        // Setup demo
        broker.createTopic("news");
        broker.subscribe("news", new PrintSubscriber("A"));
        broker.subscribe("news", new PrintSubscriber("B"));

        broker.createTopic("sports");
        broker.subscribe("sports", new PrintSubscriber("X"));
    }

    @PostMapping("/publish")
    public String publish(@RequestParam String topic,
                          @RequestParam String message) {
        broker.publish(topic, new Message(message));
        return "Message published to topic: " + topic;
    }

    @GetMapping("/topics")
    public Object listTopics() {
        return broker.getTopics();
    }
}
