package com.example.demo24.onlineexam.model;

import java.util.List;

public class Question {
    private final int questionId;
    private final String questionText;
    private final List<Option> options;
    private final int correctOptionId;

    public Question(int questionId, String questionText, List<Option> options, int correctOptionId) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.options = options;
        this.correctOptionId = correctOptionId;
    }

    public int getQuestionId() { return questionId; }
    public String getQuestionText() { return questionText; }
    public List<Option> getOptions() { return options; }
    public int getCorrectOptionId() { return correctOptionId; }
}
