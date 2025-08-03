package com.example.demo29.quora.controller;

import com.example.demo29.quora.model.*;
import com.example.demo29.quora.service.QuoraService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/quora")
public class QuoraController {

    private final QuoraService service;

    public QuoraController(QuoraService service) {
        this.service = service;
    }

    @PostMapping("/user")
    public User createUser(@RequestParam String id, @RequestParam String name) {
        return service.createUser(id, name);
    }

    @PostMapping("/ask")
    public Question ask(@RequestParam String qid,
                        @RequestParam String content,
                        @RequestParam String userId,
                        @RequestParam Set<String> tags) {
        return service.askQuestion(qid, content, userId, tags);
    }

    @PostMapping("/answer")
    public Answer answer(@RequestParam String aid,
                         @RequestParam String qid,
                         @RequestParam String content,
                         @RequestParam String userId) {
        return service.postAnswer(aid, qid, content, userId);
    }

    @PostMapping("/upvote")
    public void upvote(@RequestParam String aid) {
        service.upvote(aid);
    }

    @PostMapping("/downvote")
    public void downvote(@RequestParam String aid) {
        service.downvote(aid);
    }

    @GetMapping("/answers")
    public List<Answer> getAnswers(@RequestParam String qid) {
        return service.getAnswers(qid);
    }

    @GetMapping("/questions/by-tag")
    public List<Question> questionsByTag(@RequestParam String tag) {
        return service.searchByTag(tag);
    }

    @GetMapping("/answers/top")
    public List<Answer> getTopAnswers(@RequestParam String qid) {
        return service.getTopAnswers(qid);
    }
}
