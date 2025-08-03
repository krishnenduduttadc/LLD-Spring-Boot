package com.example.demo18.jobscheduler.controller;

import com.example.demo18.jobscheduler.model.Job;
import com.example.demo18.jobscheduler.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService service;

    public JobController(JobService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public Job register(@RequestParam String name,
                        @RequestParam String command,
                        @RequestParam int interval,
                        @RequestParam int retries) {
        return service.registerJob(name, command, interval, retries);
    }

    @GetMapping
    public List<Job> getAll() {
        return service.getAllJobs();
    }

    @GetMapping("/{id}")
    public Optional<Job> getById(@PathVariable String id) {
        return service.getJob(id);
    }
}
