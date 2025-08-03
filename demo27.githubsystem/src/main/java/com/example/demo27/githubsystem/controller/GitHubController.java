package com.example.demo27.githubsystem.controller;

import com.example.demo27.githubsystem.model.*;
import com.example.demo27.githubsystem.service.GitHubService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/github")
public class GitHubController {

    private final GitHubService service;

    public GitHubController(GitHubService service) {
        this.service = service;
    }

    @PostMapping("/user")
    public User createUser(@RequestParam String id, @RequestParam String name) {
        return service.createUser(id, name);
    }

    @PostMapping("/repo")
    public Repository createRepo(@RequestParam String repoId,
                                 @RequestParam String name,
                                 @RequestParam String ownerId) {
        return service.createRepo(repoId, name, ownerId);
    }

    @PostMapping("/branch")
    public Branch createBranch(@RequestParam String repoId,
                               @RequestParam String newBranch,
                               @RequestParam String baseBranch) {
        return service.createBranch(repoId, newBranch, baseBranch);
    }

    @PostMapping("/commit")
    public Commit commit(@RequestParam String repoId,
                         @RequestParam String branch,
                         @RequestParam String commitId,
                         @RequestParam String message,
                         @RequestParam String userId) {
        return service.commit(repoId, branch, commitId, message, userId);
    }

    @PostMapping("/pr")
    public PullRequest createPR(@RequestParam String prId,
                                @RequestParam String repoId,
                                @RequestParam String source,
                                @RequestParam String target,
                                @RequestParam String userId) {
        return service.createPullRequest(prId, repoId, source, target, userId);
    }

    @GetMapping("/commits")
    public List<Commit> getCommits(@RequestParam String repoId, @RequestParam String branch) {
        return service.getBranchCommits(repoId, branch);
    }

    @GetMapping("/prs")
    public List<PullRequest> getPRs(@RequestParam String repoId) {
        return service.getPullRequests(repoId);
    }

    @PostMapping("/pr/merge")
    public void merge(@RequestParam String prId) {
        service.mergePullRequest(prId);
    }
}
