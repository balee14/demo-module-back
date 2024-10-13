package com.terry.demo.batch.domain.test.job;

import com.terry.demo.batch.domain.test.item.BatchTestTasklet;
import com.terry.demo.core.enums.PfJobEnum;
import com.terry.demo.core.enums.PfStepEnum;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchTestTaskletJob {

    private static final Logger logger = LoggerFactory.getLogger(BatchTestTaskletJob.class);

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final BatchTestTasklet batchTestTasklet;

    @Bean
    public Job testTaskletJob(Step testTaskletStep) {
        return new JobBuilder(PfJobEnum.TEST_TASKLET_JOB.getJobId(), jobRepository)
                .start(testTaskletStep)
                .build();
    }

    @Bean
    public Step testTaskletStep() {
        return new StepBuilder(PfStepEnum.TEST_TASKLET_STEP.getStepId(), jobRepository)
                .tasklet(batchTestTasklet, transactionManager)
                .build();
    }

}
