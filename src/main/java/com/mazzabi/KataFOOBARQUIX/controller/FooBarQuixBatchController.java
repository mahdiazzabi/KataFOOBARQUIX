package com.mazzabi.KataFOOBARQUIX.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
public class FooBarQuixBatchController {

    private final JobLauncher jobLauncher;
    private final Job fooBarQuixJob;

    public FooBarQuixBatchController(JobLauncher jobLauncher, Job fooBarQuixJob) {
        this.jobLauncher = jobLauncher;
        this.fooBarQuixJob = fooBarQuixJob;
    }

    @PostMapping("/run")
    public ResponseEntity<String> runBatchJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();

            JobExecution jobExecution = jobLauncher.run(fooBarQuixJob, jobParameters);

            String createTime = jobExecution.getCreateTime() != null
                    ? jobExecution.getCreateTime().toString()
                    : "N/A";

            return ResponseEntity.ok("Batch job created at: " + createTime);
        } catch (JobExecutionAlreadyRunningException |
                 JobInstanceAlreadyCompleteException |
                 JobParametersInvalidException |
                 JobRestartException e) {
            return ResponseEntity.status(500).body("Error starting batch job: " + e.getMessage());
        }
    }
}
