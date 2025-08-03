package com.example.demo28.linkedin.model;

public class Job {
    private final String jobId;
    private final String title;
    private final String company;
    private final String postedBy;

    public Job(String jobId, String title, String company, String postedBy) {
        this.jobId = jobId;
        this.title = title;
        this.company = company;
        this.postedBy = postedBy;
    }

    public String getJobId() { return jobId; }
    public String getTitle() { return title; }
    public String getCompany() { return company; }
    public String getPostedBy() { return postedBy; }
}
