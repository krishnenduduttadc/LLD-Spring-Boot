package com.example.demo28.linkedin.model;

public class Application {
    private final String applicationId;
    private final String jobId;
    private final String applicantId;

    public Application(String applicationId, String jobId, String applicantId) {
        this.applicationId = applicationId;
        this.jobId = jobId;
        this.applicantId = applicantId;
    }

    public String getApplicationId() { return applicationId; }
    public String getJobId() { return jobId; }
    public String getApplicantId() { return applicantId; }
}
