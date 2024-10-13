package com.terry.demo.batch.controller;

import com.terry.demo.batch.domain.test.BatchTestService;
import com.terry.demo.batch.domain.test.dto.request.BatchTestRequest;
import com.terry.demo.core.dto.common.CommonResponseEntity;
import com.terry.demo.core.dto.common.CommonResponseEntityType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/batch/v1")
@RequiredArgsConstructor
public class BatchTestController {

    private static final Logger logger = LoggerFactory.getLogger(BatchTestController.class);

    private final JobLauncher jobLauncher;
    private final JobRegistry jobRegistry;
    private final BatchTestService batchTestService;

    /**
     * 테스트 관련 배치
     */



    /**
     * batch 테스트
     */
    @PostMapping("/test")
    public ResponseEntity<?> batchTest(@RequestBody BatchTestRequest batchTestRequest) {
        /*
        logger.info("테스트 등록 런처 시작");
        String time = LocalDateTime.now().toString();

        try {

            Job job = jobRegistry.getJob("testJob");
            JobParametersBuilder jobParam = new JobParametersBuilder()
                    .addString("batchApiId", batchTestRequest.getBatchApiId());

            JobExecution execution = jobLauncher.run(job, jobParam.toJobParameters());
            Object obj = execution.getExecutionContext();
            logger.info("obj -> {}", obj);

        } catch (NoSuchJobException | JobInstanceAlreadyCompleteException |
                 JobExecutionAlreadyRunningException | JobParametersInvalidException |
                 JobRestartException e) {

            logger.error("테스트 스케줄러 예외 발생!! -> {}", e.getMessage());
            throw new RuntimeException(e);

        }
        */
        batchTestService.batchTest(batchTestRequest);


        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK)
                , CommonResponseEntityType.OK.getHttpStatus());

    }

}

