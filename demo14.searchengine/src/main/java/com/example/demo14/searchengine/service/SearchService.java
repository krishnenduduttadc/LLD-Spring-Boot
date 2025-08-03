package com.example.demo14.searchengine.service;

import com.example.demo14.searchengine.index.InvertedIndex;
import com.example.demo14.searchengine.model.Document;
import com.example.demo14.searchengine.model.TokenUtil;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private final InvertedIndex index;

    public SearchService(InvertedIndex index) {
        this.index = index;
    }

    public void addDocument(String id, String title, String content) {
        index.addDocument(new Document(id, title, content));
    }

    public List<Document> search(String query) {
        List<String> tokens = TokenUtil.tokenize(query);

        Set<String> matchedDocIds = new HashSet<>();
        for (String token : tokens) {
            matchedDocIds.addAll(index.search(token));
        }

        // Simple ranking: #matchedTokens in doc
        Map<String, Integer> score = new HashMap<>();
        for (String token : tokens) {
            for (String docId : index.search(token)) {
                score.put(docId, score.getOrDefault(docId, 0) + 1);
            }
        }

        return matchedDocIds.stream()
                .sorted((a, b) -> Integer.compare(score.get(b), score.get(a)))
                .map(id -> index.getDocumentsByIds(Set.of(id)).iterator().next())
                .collect(Collectors.toList());
    }
}
