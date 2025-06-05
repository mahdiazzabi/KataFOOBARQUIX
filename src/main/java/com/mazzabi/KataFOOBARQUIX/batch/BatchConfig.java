package com.mazzabi.KataFOOBARQUIX.batch;

import com.mazzabi.KataFOOBARQUIX.service.IFooBarQuixService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final IFooBarQuixService fooBarQuixService;

    public BatchConfig(JobRepository jobRepository,
                       PlatformTransactionManager transactionManager,
                       IFooBarQuixService fooBarQuixService) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.fooBarQuixService = fooBarQuixService;
    }

    @Bean
    public Job fooBarQuixJob() throws IOException {
        return new JobBuilder("fooBarQuixJob", jobRepository)
                .start(fooBarQuixStep())
                .listener(jobExecutionListener())
                .build();
    }

    @Bean
    public Step fooBarQuixStep() throws IOException {
        Resource input = new FileSystemResource(Paths.get("input", "input.txt"));
        NumberItemReader reader = new NumberItemReader(input);
        FooBarQuixProcessor processor = new FooBarQuixProcessor(fooBarQuixService);
        ResultItemWriter writer = new ResultItemWriter(Paths.get("output", "output.txt"));

        return new StepBuilder("fooBarQuixStep", jobRepository)
                .<String, String>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public JobExecutionListener jobExecutionListener() {
        return new JobExecutionListener() {
            @Override
            public void beforeJob(JobExecution jobExecution) {
                jobExecution.setStartTime(LocalDateTime.now());
                System.out.println("Job started at: " + jobExecution.getStartTime());
            }

            @Override
            public void afterJob(JobExecution jobExecution) {
                System.out.println("Job ended at: " + jobExecution.getEndTime());
            }
        };
    }
}