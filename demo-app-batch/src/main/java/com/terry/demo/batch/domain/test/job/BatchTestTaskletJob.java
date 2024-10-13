package com.terry.demo.batch.domain.test.job;

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
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchTestTaskletJob {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job testTaskletJob(Step testTaskletStep) {
        return new JobBuilder(PfJobEnum.TEST_TASKLET_JOB.getJobId(), jobRepository)
                .start(testTaskletStep)
                .build();
    }

    @Bean
    public Step testTaskletStep() {
        return new StepBuilder(PfStepEnum.TEST_TASKLET_STEP.getStepId(), jobRepository)
                .tasklet(testTasklet(), transactionManager)
                .build();
    }

    @Bean
    public Tasklet testTasklet() {

        return (contribution, chunkContext) -> {

            int count = 0;
            final int MAX_COUNT = 100000;

            while (count < MAX_COUNT) {
                count++;

                // 데이터 생성
                String idEmail = "test" + count + "@pomg.co.kr";
                String description = "test-" + count;

                // 데이터베이스에 등록
                MapSqlParameterSource paramSource = new MapSqlParameterSource();
                paramSource.addValue("idEmail", idEmail);
                paramSource.addValue("description", description);

                String sql = "INSERT INTO pf_test_batch (id_email, description) VALUES (:idEmail, :description)";
                jdbcTemplate.update(sql, paramSource);
            }

            System.out.println("Executing Tasklet Step...");
            return RepeatStatus.FINISHED;  // 작업 완료 후 종료
        };

    }

}
