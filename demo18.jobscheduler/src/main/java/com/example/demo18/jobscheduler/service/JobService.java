package com.example.demo18.jobscheduler.service;

import com.example.demo18.jobscheduler.model.Job;
import com.example.demo18.jobscheduler.model.JobStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;

@Service
public class JobService {

    private final Map<String, Job> jobMap = new ConcurrentHashMap<>();
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);

    public Job registerJob(String name, String command, int interval, int retries) {
        Job job = new Job(name, command, interval, retries);
        jobMap.put(job.getId(), job);
        return job;
    }

    public List<Job> getAllJobs() {
        return new ArrayList<>(jobMap.values());
    }

    public Optional<Job> getJob(String id) {
        return Optional.ofNullable(jobMap.get(id));
    }

    public void runDueJobs() {
        LocalDateTime now = LocalDateTime.now();
        for (Job job : jobMap.values()) {
            if (job.getNextRun().isBefore(now) && job.getStatus() != JobStatus.RUNNING) {
                executeJob(job);
            }
        }
    }

    private void executeJob(Job job) {
        job.setStatus(JobStatus.RUNNING);
        executor.submit(() -> {
            try {
                System.out.println("[Running] " + job.getCommand());
                Process process = Runtime.getRuntime().exec(job.getCommand());
                int exitCode = process.waitFor();
                if (exitCode == 0) {
                    job.setStatus(JobStatus.SUCCESS);
                    job.updateNextRun();
                } else {
                    handleFailure(job);
                }
            } catch (Exception e) {
                handleFailure(job);
            }
        });
    }

    private void handleFailure(Job job) {
        job.incrementRetry();
        if (job.getRetryCount() > job.getMaxRetries()) {
            job.setStatus(JobStatus.FAILED);
        } else {
            job.setStatus(JobStatus.PENDING);
            job.updateNextRun();
        }
    }
}
