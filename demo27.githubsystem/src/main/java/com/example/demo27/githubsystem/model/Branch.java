package com.example.demo27.githubsystem.model;

import java.util.*;

public class Branch {
    private final String name;
    private final List<Commit> commits;

    public Branch(String name) {
        this.name = name;
        this.commits = new ArrayList<>();
    }

    public String getName() { return name; }
    public List<Commit> getCommits() { return commits; }

    public void addCommit(Commit commit) {
        commits.add(commit);
    }
}
