package com.example.demo27.githubsystem.model;

public class PullRequest {
    private final String prId;
    private final String repoId;
    private final String sourceBranch;
    private final String targetBranch;
    private final String createdBy;
    private PRStatus status;

    public PullRequest(String prId, String repoId, String sourceBranch,
                       String targetBranch, String createdBy) {
        this.prId = prId;
        this.repoId = repoId;
        this.sourceBranch = sourceBranch;
        this.targetBranch = targetBranch;
        this.createdBy = createdBy;
        this.status = PRStatus.OPEN;
    }

    public String getPrId() { return prId; }
    public String getRepoId() { return repoId; }
    public String getSourceBranch() { return sourceBranch; }
    public String getTargetBranch() { return targetBranch; }
    public String getCreatedBy() { return createdBy; }
    public PRStatus getStatus() { return status; }
    public void setStatus(PRStatus status) { this.status = status; }
}
