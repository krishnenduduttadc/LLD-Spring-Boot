package com.example.demo29.quora.model;

import java.util.*;

public class Question {
    private final String questionId;
    private final String content;
    private final String authorId;
    private final Set<String> tags;

    public Question(String questionId, String content, String authorId, Set<String> tags) {
        this.questionId = questionId;
        this.content = content;
        this.authorId = authorId;
        this.tags = tags;
    }

    public String getQuestionId() { return questionId; }
    public String getContent() { return content; }
    public String getAuthorId() { return authorId; }
    public Set<String> getTags() { return tags; }
}
