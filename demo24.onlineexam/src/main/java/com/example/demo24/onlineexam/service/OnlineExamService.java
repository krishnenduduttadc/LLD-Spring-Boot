package com.example.demo24.onlineexam.service;

import com.example.demo24.onlineexam.model.*;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OnlineExamService {

    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Map<String, TestPaper> testPapers = new ConcurrentHashMap<>();
    private final List<Submission> submissions = new ArrayList<>();

    public User createUser(String userId, String name, boolean isAdmin) {
        User user = new User(userId, name, isAdmin);
        users.put(userId, user);
        return user;
    }

    public TestPaper createTest(String testId, String title, List<Question> questions, int durationMinutes) {
        TestPaper test = new TestPaper(testId, title, questions, durationMinutes);
        testPapers.put(testId, test);
        return test;
    }

    public List<Question> getQuestionsForTest(String testId) {
        TestPaper test = testPapers.get(testId);
        return test != null ? test.getQuestions() : Collections.emptyList();
    }

    public Submission submitTest(String userId, String testId, Map<Integer, Integer> answers) {
        TestPaper test = testPapers.get(testId);
        if (test == null) throw new RuntimeException("Test not found");

        int score = 0;
        for (Question q : test.getQuestions()) {
            if (answers.containsKey(q.getQuestionId()) &&
                q.getCorrectOptionId() == answers.get(q.getQuestionId())) {
                score++;
            }
        }

        Submission submission = new Submission(userId, testId, answers, score);
        submissions.add(submission);
        return submission;
    }

    public List<Submission> getSubmissionsForTest(String testId) {
        List<Submission> result = new ArrayList<>();
        for (Submission s : submissions) {
            if (s.getTestId().equals(testId)) result.add(s);
        }
        return result;
    }
}
