package com.terry.demo.batch.domain.test;

import com.terry.demo.core.enums.PfJobEnum;
import com.terry.demo.core.enums.PfStepEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchTestJob {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job testJob(Step testStep) {
        return new JobBuilder(PfJobEnum.TEST_JOB.getJobId(), jobRepository)
                .start(testStep)
                .build();
    }

    @Bean
    public Step testStep() {
        return new StepBuilder(PfStepEnum.TEST_STEP.getStepId(), jobRepository)
                .tasklet(testTasklet(), transactionManager)
                .build();
    }

    @Bean
    public Tasklet testTasklet() {
        return (contribution, chunkContext) -> {
            System.out.println("Executing Tasklet Step...");
            return RepeatStatus.FINISHED;  // 작업 완료 후 종료
        };
    }

}
