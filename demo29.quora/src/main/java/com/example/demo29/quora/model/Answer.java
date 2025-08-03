package com.example.demo29.quora.model;

import java.time.LocalDateTime;

public class Answer {
    private final String answerId;
    private final String questionId;
    private final String content;
    private final String authorId;
    private final LocalDateTime timestamp;
    private int upvotes = 0;
    private int downvotes = 0;

    public Answer(String answerId, String questionId, String content, String authorId) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.content = content;
        this.authorId = authorId;
        this.timestamp = LocalDateTime.now();
    }

    public String getAnswerId() { return answerId; }
    public String getQuestionId() { return questionId; }
    public String getContent() { return content; }
    public String getAuthorId() { return authorId; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public int getUpvotes() { return upvotes; }
    public int getDownvotes() { return downvotes; }

    public void upvote() { upvotes++; }
    public void downvote() { downvotes++; }
}
