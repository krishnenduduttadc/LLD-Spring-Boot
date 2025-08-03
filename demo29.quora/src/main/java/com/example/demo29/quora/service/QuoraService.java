package com.example.demo29.quora.service;

import com.example.demo29.quora.model.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class QuoraService {

    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Map<String, Question> questions = new ConcurrentHashMap<>();
    private final Map<String, Answer> answers = new ConcurrentHashMap<>();
    private final Map<String, List<String>> questionAnswers = new ConcurrentHashMap<>();

    // Users
    public User createUser(String id, String name) {
        User user = new User(id, name);
        users.put(id, user);
        return user;
    }

    // Questions
    public Question askQuestion(String qid, String content, String userId, Set<String> tags) {
        Question q = new Question(qid, content, userId, tags);
        questions.put(qid, q);
        questionAnswers.put(qid, new ArrayList<>());
        return q;
    }

    public List<Question> searchByTag(String tag) {
        return questions.values().stream()
                .filter(q -> q.getTags().contains(tag))
                .collect(Collectors.toList());
    }

    // Answers
    public Answer postAnswer(String aid, String qid, String content, String userId) {
        if (!questions.containsKey(qid)) throw new RuntimeException("Question not found");

        Answer answer = new Answer(aid, qid, content, userId);
        answers.put(aid, answer);
        questionAnswers.get(qid).add(aid);
        return answer;
    }

    public List<Answer> getAnswers(String qid) {
        if (!questionAnswers.containsKey(qid)) return Collections.emptyList();
        return questionAnswers.get(qid).stream().map(answers::get).collect(Collectors.toList());
    }

    public void upvote(String aid) {
        answers.get(aid).upvote();
    }

    public void downvote(String aid) {
        answers.get(aid).downvote();
    }

    public List<Answer> getTopAnswers(String qid) {
        return getAnswers(qid).stream()
                .sorted((a, b) -> Integer.compare(b.getUpvotes() - b.getDownvotes(), a.getUpvotes() - a.getDownvotes()))
                .limit(5)
                .collect(Collectors.toList());
    }
}
