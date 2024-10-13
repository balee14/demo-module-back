package com.terry.demo.test;

import com.terry.demo.batch.domain.test.BatchTestService;
import com.terry.demo.batch.domain.test.dto.request.BatchTestRequest;
import com.terry.demo.batch.domain.test.dto.response.BatchTestResponse;
import com.terry.demo.core.enums.PfJobEnum;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Log4j2
@SpringBootTest
@SpringBatchTest
class BatchTestServiceTest {

    @Autowired
    BatchTestService batchTestService;

    String obParameters;

    @BeforeEach
    public void setUp() {
        obParameters = LocalDateTime.now().toString();
    }

    @Test
    @DisplayName("item 테스트 실행")
    void batchTestItem() {

        //given
        BatchTestRequest batchTestRequest = BatchTestRequest.builder()
                .jobName(PfJobEnum.TEST_ITEM_JOB)
                .jobParameters(obParameters)
                .build();

        //when
        BatchTestResponse batchTestResponse = batchTestService.batchTestItem(batchTestRequest);

        //then
        assertNotNull(batchTestResponse);
        log.info("batchTestResponse -> {}", batchTestResponse);

    }

    @Test
    void batchTestTasklet() {

        //given
        BatchTestRequest batchTestRequest = BatchTestRequest.builder()
                .jobName(PfJobEnum.TEST_ITEM_JOB)
                .jobParameters(obParameters)
                .build();

        //when
        BatchTestResponse batchTestResponse = batchTestService.batchTestTasklet(batchTestRequest);

        //then
        assertNotNull(batchTestResponse);
        log.info("batchTestResponse -> {}", batchTestResponse);

    }
}