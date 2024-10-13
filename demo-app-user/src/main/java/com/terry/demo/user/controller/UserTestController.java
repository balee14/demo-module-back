package com.terry.demo.user.controller;


import com.terry.demo.core.dto.common.CommonResponseEntity;
import com.terry.demo.core.dto.common.CommonResponseEntityType;
import com.terry.demo.user.domain.test.UserTestService;
import com.terry.demo.user.domain.test.dto.request.UserTestRequest;
import com.terry.demo.user.domain.test.dto.response.UserTestDtoQueryResponse;
import com.terry.demo.user.domain.test.dto.response.UserTestDtoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user/v1")
public class UserTestController {

    /**
     * 사용자 관리
     */

    private final UserTestService userTestService;

    /**
     * 모든 test 목록 조회
     */
    /*
    @GetMapping("/test/tests")
    public ResponseEntity<?> getUserTestList(@ModelAttribute TestListRequest testListRequest, Pageable pageable) {
        return userTestService.getUserTestList(testListRequest, pageable);
    }
    */

    /**
     * user test 조회
     */
    @GetMapping("/test/testId/{testId}")
    public ResponseEntity<?> getUserTestById(@PathVariable String testId) {

        UserTestDtoQueryResponse userTestDtoQueryResponse = userTestService.getUserTestById(testId);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, userTestDtoQueryResponse)
                , CommonResponseEntityType.OK.getHttpStatus());

    }

    /**
     * user test 등록
     */
    @PostMapping("/test")
    public ResponseEntity<?> userTestSave(@RequestBody UserTestRequest userTestRequest) {

        UserTestDtoResponse userTestDtoResponse = userTestService.userTestSave(userTestRequest);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, userTestDtoResponse)
                , CommonResponseEntityType.OK.getHttpStatus());

    }

    /**
     * user test 등록
     */
    @PostMapping("/test/file")
    public ResponseEntity<?> userTestFileSave(@ModelAttribute UserTestRequest userTestRequest) throws IOException {

        UserTestDtoResponse userTestDtoResponse = userTestService.userTestFileSave(userTestRequest);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, userTestDtoResponse)
                , CommonResponseEntityType.OK.getHttpStatus());

    }

    /**
     * user test 업데이트
     */
    /*
    @PutMapping("/test")
    public ResponseEntity<?> userTestUpdate(@RequestBody TestUpdateRequest testUpdateRequest) {
        return userTestService.userTestUpdate(testUpdateRequest);
    }
    */



}


