package com.example.demo18.jobscheduler.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Job {
    private final String id;
    private final String name;
    private final String command;
    private final int intervalSeconds;
    private final int maxRetries;
    private int retryCount;
    private JobStatus status;
    private LocalDateTime nextRun;

    public Job(String name, String command, int intervalSeconds, int maxRetries) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.command = command;
        this.intervalSeconds = intervalSeconds;
        this.maxRetries = maxRetries;
        this.status = JobStatus.PENDING;
        this.retryCount = 0;
        this.nextRun = LocalDateTime.now().plusSeconds(intervalSeconds);
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getCommand() { return command; }
    public int getIntervalSeconds() { return intervalSeconds; }
    public int getMaxRetries() { return maxRetries; }
    public int getRetryCount() { return retryCount; }
    public void incrementRetry() { retryCount++; }
    public JobStatus getStatus() { return status; }
    public void setStatus(JobStatus status) { this.status = status; }
    public LocalDateTime getNextRun() { return nextRun; }
    public void updateNextRun() { this.nextRun = LocalDateTime.now().plusSeconds(intervalSeconds); }
}
