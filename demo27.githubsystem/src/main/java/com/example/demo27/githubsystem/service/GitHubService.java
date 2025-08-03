package com.example.demo27.githubsystem.service;

import com.example.demo27.githubsystem.model.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GitHubService {

    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Map<String, Repository> repositories = new ConcurrentHashMap<>();
    private final Map<String, PullRequest> pullRequests = new ConcurrentHashMap<>();

    public User createUser(String userId, String name) {
        User user = new User(userId, name);
        users.put(userId, user);
        return user;
    }

    public Repository createRepo(String repoId, String name, String ownerId) {
        if (!users.containsKey(ownerId)) throw new RuntimeException("User not found");
        Repository repo = new Repository(repoId, ownerId, name);
        repositories.put(repoId, repo);
        return repo;
    }

    public Branch createBranch(String repoId, String newBranch, String baseBranch) {
        Repository repo = repositories.get(repoId);
        if (repo == null) throw new RuntimeException("Repo not found");
        if (!repo.getBranches().containsKey(baseBranch)) throw new RuntimeException("Base branch not found");

        Branch base = repo.getBranches().get(baseBranch);
        Branch clone = new Branch(newBranch);
        clone.getCommits().addAll(base.getCommits());
        repo.getBranches().put(newBranch, clone);
        return clone;
    }

    public Commit commit(String repoId, String branchName, String commitId, String message, String userId) {
        Repository repo = repositories.get(repoId);
        Branch branch = repo.getBranches().get(branchName);
        if (branch == null) throw new RuntimeException("Branch not found");
        Commit commit = new Commit(commitId, message, userId);
        branch.addCommit(commit);
        return commit;
    }

    public PullRequest createPullRequest(String prId, String repoId, String source, String target, String userId) {
        PullRequest pr = new PullRequest(prId, repoId, source, target, userId);
        pullRequests.put(prId, pr);
        return pr;
    }

    public List<Commit> getBranchCommits(String repoId, String branch) {
        return repositories.get(repoId).getBranches().get(branch).getCommits();
    }

    public List<PullRequest> getPullRequests(String repoId) {
        List<PullRequest> result = new ArrayList<>();
        for (PullRequest pr : pullRequests.values()) {
            if (pr.getRepoId().equals(repoId)) result.add(pr);
        }
        return result;
    }

    public void mergePullRequest(String prId) {
        PullRequest pr = pullRequests.get(prId);
        if (pr == null || pr.getStatus() != PRStatus.OPEN) throw new RuntimeException("PR not open");

        Repository repo = repositories.get(pr.getRepoId());
        Branch source = repo.getBranches().get(pr.getSourceBranch());
        Branch target = repo.getBranches().get(pr.getTargetBranch());

        target.getCommits().addAll(source.getCommits());
        pr.setStatus(PRStatus.MERGED);
    }
}
