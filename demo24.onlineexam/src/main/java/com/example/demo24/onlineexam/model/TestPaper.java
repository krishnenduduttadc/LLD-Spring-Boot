package com.example.demo24.onlineexam.model;

import java.util.*;

public class TestPaper {
    private final String testId;
    private final String title;
    private final List<Question> questions;
    private final int durationMinutes; // Timer

    public TestPaper(String testId, String title, List<Question> questions, int durationMinutes) {
        this.testId = testId;
        this.title = title;
        this.questions = questions;
        this.durationMinutes = durationMinutes;
    }

    public String getTestId() { return testId; }
    public String getTitle() { return title; }
    public List<Question> getQuestions() { return questions; }
    public int getDurationMinutes() { return durationMinutes; }
}
