package com.example.demo24.onlineexam.model;

import java.time.LocalDateTime;
import java.util.Map;

public class Submission {
    private final String userId;
    private final String testId;
    private final Map<Integer, Integer> answers; // questionId → optionId
    private final LocalDateTime submittedAt;
    private final int score;

    public Submission(String userId, String testId, Map<Integer, Integer> answers, int score) {
        this.userId = userId;
        this.testId = testId;
        this.answers = answers;
        this.submittedAt = LocalDateTime.now();
        this.score = score;
    }

    public String getUserId() { return userId; }
    public String getTestId() { return testId; }
    public Map<Integer, Integer> getAnswers() { return answers; }
    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public int getScore() { return score; }
}
