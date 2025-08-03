package com.example.demo23.googledocs.service;

import com.example.demo23.googledocs.model.*;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class GoogleDocsService {

    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Map<String, Document> documents = new ConcurrentHashMap<>();
    private final List<Edit> editHistory = new ArrayList<>();

    public User createUser(String id, String name) {
        User user = new User(id, name);
        users.put(id, user);
        return user;
    }

    public Document createDocument(String docId, String title, String content, String ownerId) {
        if (!users.containsKey(ownerId)) throw new RuntimeException("Owner not found");

        Document doc = new Document(docId, title, content, ownerId);
        documents.put(docId, doc);
        editHistory.add(new Edit(ownerId, docId, content));
        return doc;
    }

    public Document editDocument(String docId, String userId, String newContent) {
        Document doc = documents.get(docId);
        if (doc == null || !hasEditAccess(doc, userId)) throw new RuntimeException("Access denied");

        doc.setContent(newContent);
        editHistory.add(new Edit(userId, docId, newContent));
        return doc;
    }

    public void grantAccess(String docId, String userId, AccessType accessType) {
        Document doc = documents.get(docId);
        if (doc == null) throw new RuntimeException("Document not found");

        doc.getCollaborators().put(userId, accessType);
    }

    public List<Edit> getEditHistory(String docId) {
        return editHistory.stream()
                .filter(e -> e.getDocId().equals(docId))
                .sorted(Comparator.comparing(Edit::getTimestamp))
                .collect(Collectors.toList());
    }

    public Document getDocument(String docId, String userId) {
        Document doc = documents.get(docId);
        if (doc == null || !doc.getCollaborators().containsKey(userId)) throw new RuntimeException("No access");
        return doc;
    }

    private boolean hasEditAccess(Document doc, String userId) {
        return doc.getCollaborators().getOrDefault(userId, AccessType.VIEWER) != AccessType.VIEWER;
    }
}
