package com.example.demo27.githubsystem.model;

import java.util.*;

public class Repository {
    private final String repoId;
    private final String ownerId;
    private final String name;
    private final Map<String, Branch> branches;

    public Repository(String repoId, String ownerId, String name) {
        this.repoId = repoId;
        this.ownerId = ownerId;
        this.name = name;
        this.branches = new HashMap<>();
        this.branches.put("main", new Branch("main")); // default branch
    }

    public String getRepoId() { return repoId; }
    public String getOwnerId() { return ownerId; }
    public String getName() { return name; }
    public Map<String, Branch> getBranches() { return branches; }
}
