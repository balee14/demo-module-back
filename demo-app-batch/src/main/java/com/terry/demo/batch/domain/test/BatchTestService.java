package com.terry.demo.batch.domain.test;

import com.terry.demo.batch.domain.test.dto.request.BatchTestRequest;
import com.terry.demo.batch.domain.test.dto.response.BatchTestDtoResponse;
import com.terry.demo.core.enums.PfJobEnum;
import com.terry.demo.test.repository.TestRepository;
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

import java.time.LocalDateTime;

@Log4j2
@Service
@RequiredArgsConstructor
public class BatchTestService {

    private static final Logger logger = LoggerFactory.getLogger(BatchTestService.class);

    private final JobLauncher jobLauncher;
    private final JobRegistry jobRegistry;
    //private final Job batchTestJob;

    private final TestRepository testRepository;

    /**
     * admin member 등록
     */
    public BatchTestDtoResponse batchTest(BatchTestRequest batchTestRequest) {

        logger.info("테스트 등록 런처 시작");
        String time = LocalDateTime.now().toString();


        try {

            Job job = jobRegistry.getJob(PfJobEnum.TEST_JOB.getJobId());
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("batchApiId", batchTestRequest.getBatchApiId())
                    .toJobParameters();

            JobExecution execution = jobLauncher.run(job, jobParameters);
            Object obj = execution.getExecutionContext();
            logger.info("obj -> {}", obj);

        } catch (JobInstanceAlreadyCompleteException | JobExecutionAlreadyRunningException |
                 JobParametersInvalidException | JobRestartException | NoSuchJobException e) {

            logger.error("테스트 스케줄러 예외 발생!! -> {}", e.getMessage());
            throw new RuntimeException(e);

        }

        return BatchTestDtoResponse.builder()
                .build();
    }

}

