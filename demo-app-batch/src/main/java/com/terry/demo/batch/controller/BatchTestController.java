package com.terry.demo.batch.controller;

import com.terry.demo.batch.domain.test.BatchTestService;
import com.terry.demo.batch.domain.test.dto.request.BatchTestRequest;
import com.terry.demo.batch.domain.test.dto.response.BatchTestResponse;
import com.terry.demo.core.dto.common.CommonResponseEntity;
import com.terry.demo.core.dto.common.CommonResponseEntityType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final BatchTestService batchTestService;

    /**
     * 테스트 관련 배치
     */

    /**
     * batch item 테스트
     */
    @PostMapping("/test/item")
    public ResponseEntity<?> batchTestItem(@RequestBody BatchTestRequest batchTestRequest) {

        BatchTestResponse batchTestResponse = batchTestService.batchTestItem(batchTestRequest);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, batchTestResponse)
                , CommonResponseEntityType.OK.getHttpStatus());

    }

    /**
     * batch tasklet 테스트
     */
    @PostMapping("/test/tasklet")
    public ResponseEntity<?> batchTestTasklet(@RequestBody BatchTestRequest batchTestRequest) {

        BatchTestResponse batchTestResponse = batchTestService.batchTestTasklet(batchTestRequest);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, batchTestResponse)
                , CommonResponseEntityType.OK.getHttpStatus());

    }

}

