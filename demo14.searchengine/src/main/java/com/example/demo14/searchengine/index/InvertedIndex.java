package com.example.demo14.searchengine.index;

import com.example.demo14.searchengine.model.Document;
import com.example.demo14.searchengine.model.TokenUtil;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InvertedIndex {

    private final Map<String, Set<String>> index = new HashMap<>(); // word → docIds
    private final Map<String, Document> documents = new HashMap<>(); // docId → doc

    public void addDocument(Document doc) {
        documents.put(doc.getId(), doc);
        List<String> tokens = TokenUtil.tokenize(doc.getTitle() + " " + doc.getContent());

        for (String token : tokens) {
            index.computeIfAbsent(token, k -> new HashSet<>()).add(doc.getId());
        }
    }

    public Set<String> search(String keyword) {
        return index.getOrDefault(keyword.toLowerCase(), Collections.emptySet());
    }

    public Collection<Document> getDocumentsByIds(Set<String> ids) {
        List<Document> results = new ArrayList<>();
        for (String id : ids) {
            if (documents.containsKey(id)) {
                results.add(documents.get(id));
            }
        }
        return results;
    }

    public Collection<Document> getAllDocuments() {
        return documents.values();
    }
}
