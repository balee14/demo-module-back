package com.terry.demo.batch.domain.test;

import com.terry.demo.batch.domain.test.dto.request.BatchTestRequest;
import com.terry.demo.batch.domain.test.dto.response.BatchTestResponse;
import com.terry.demo.core.enums.PfJobEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class BatchTestService {

    private static final Logger logger = LoggerFactory.getLogger(BatchTestService.class);

    private final JobLauncher jobLauncher;
    private final JobRegistry jobRegistry;

    /**
     * admin member 등록
     */
    public BatchTestResponse batchTestItem(BatchTestRequest batchTestRequest) {

        logger.info("테스트 Item 등록 런처 시작");

        try {

            Job job = jobRegistry.getJob(PfJobEnum.TEST_ITEM_JOB.getJobId());
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("startDateTime", batchTestRequest.getJobParameters())
                    .toJobParameters();

            JobExecution execution = jobLauncher.run(job, jobParameters);
            Object obj = execution.getExecutionContext();
            logger.info("obj -> {}", obj);
            return BatchTestResponse.builder()
                    .result(obj)
                    .build();

        } catch (JobInstanceAlreadyCompleteException | JobExecutionAlreadyRunningException |
                 JobParametersInvalidException | JobRestartException | NoSuchJobException e) {

            logger.error("테스트 스케줄러 예외 발생!! -> {}", e.getMessage());
            throw new RuntimeException(e);

        }


    }

    /**
     * admin member 등록
     */
    public BatchTestResponse batchTestTasklet(BatchTestRequest batchTestRequest) {

        logger.info("테스트 Tasklet 등록 런처 시작");


        try {

            Job job = jobRegistry.getJob(PfJobEnum.TEST_TASKLET_JOB.getJobId());
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("startDateTime", batchTestRequest.getJobParameters())
                    .toJobParameters();

            JobExecution execution = jobLauncher.run(job, jobParameters);
            Object obj = execution.getExecutionContext();
            logger.info("obj -> {}", obj);
            return BatchTestResponse.builder()
                    .result(obj)
                    .build();

        } catch (JobInstanceAlreadyCompleteException | JobExecutionAlreadyRunningException |
                 JobParametersInvalidException | JobRestartException | NoSuchJobException e) {

            logger.error("테스트 스케줄러 예외 발생!! -> {}", e.getMessage());
            throw new RuntimeException(e);

        }

    }



}

