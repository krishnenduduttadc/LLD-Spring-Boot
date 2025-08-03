package com.example.demo24.onlineexam.controller;

import com.example.demo24.onlineexam.model.*;
import com.example.demo24.onlineexam.service.OnlineExamService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/exam")
public class OnlineExamController {

    private final OnlineExamService service;

    public OnlineExamController(OnlineExamService service) {
        this.service = service;
    }

    @PostMapping("/user")
    public User createUser(@RequestParam String userId,
                           @RequestParam String name,
                           @RequestParam boolean isAdmin) {
        return service.createUser(userId, name, isAdmin);
    }

    @PostMapping("/test")
    public TestPaper createTest(@RequestBody TestPaper testPaper) {
        return service.createTest(testPaper.getTestId(),
                                  testPaper.getTitle(),
                                  testPaper.getQuestions(),
                                  testPaper.getDurationMinutes());
    }

    @GetMapping("/questions")
    public List<Question> getQuestions(@RequestParam String testId) {
        return service.getQuestionsForTest(testId);
    }

    @PostMapping("/submit")
    public Submission submit(@RequestParam String userId,
                             @RequestParam String testId,
                             @RequestBody Map<Integer, Integer> answers) {
        return service.submitTest(userId, testId, answers);
    }

    @GetMapping("/submissions")
    public List<Submission> submissions(@RequestParam String testId) {
        return service.getSubmissionsForTest(testId);
    }
}
