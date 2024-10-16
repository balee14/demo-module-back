package com.terry.demo.batch.domain.test.job;


import com.terry.demo.batch.domain.test.item.BatchTestItemProcessor;
import com.terry.demo.batch.domain.test.item.BatchTestItemReader;
import com.terry.demo.batch.domain.test.item.BatchTestItemWriter;
import com.terry.demo.core.entity.PfTestBatch;
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
public class BatchTestItemJob {

    private static final Logger logger = LoggerFactory.getLogger(BatchTestItemJob.class);

    private final JobRepository jobRepository;
    private final BatchTestItemReader batchTestItemReader;
    private final BatchTestItemProcessor batchTestItemProcessor;
    private final BatchTestItemWriter batchTestItemWriter;

    @Bean
    public Job testItemJob(Step testItemStep) {
        return new JobBuilder(PfJobEnum.TEST_ITEM_JOB.getJobId(), jobRepository)
                .start(testItemStep)
                .build();
    }

    @Bean
    public Step testItemStep(PlatformTransactionManager transactionManager) {
        return new StepBuilder(PfStepEnum.TEST_ITEM_STEP.getStepId(), jobRepository)
                .<PfTestBatch, PfTestBatch>chunk(10, transactionManager)
                .reader(batchTestItemReader)
                .processor(batchTestItemProcessor)
                .writer(batchTestItemWriter)
                .build();
    }

}
