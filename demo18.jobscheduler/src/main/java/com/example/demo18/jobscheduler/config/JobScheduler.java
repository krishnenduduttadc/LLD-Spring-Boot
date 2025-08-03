package com.example.demo18.jobscheduler.config;

import com.example.demo18.jobscheduler.service.JobService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobScheduler {

    private final JobService jobService;

    public JobScheduler(JobService jobService) {
        this.jobService = jobService;
    }

    @Scheduled(fixedRate = 5000) // every 5 seconds
    public void pollJobs() {
        jobService.runDueJobs();
    }
}
