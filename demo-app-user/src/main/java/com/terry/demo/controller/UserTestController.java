package com.terry.demo.controller;

import com.terry.demo.core.entity.PfTest;
import com.terry.demo.test.dto.TestRequest;
import com.terry.demo.service.test.UserTestService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     *
     * @param
     * @return
     */
    /*
    @GetMapping("/test/tests")
    public ResponseEntity<?> getUserTestList(@ModelAttribute TestListRequest testListRequest, Pageable pageable) {
        return userTestService.getUserTestList(testListRequest, pageable);
    }
    */

    /**
     * test 조회
     *
     * @param
     * @return
     */
    @GetMapping("/test/testId/{testId}")
    public ResponseEntity<?> getUserTestById(@PathVariable("testId") Long testId) {
        return userTestService.getUserTestById(testId);
    }

    /**
     * test 등록
     *
     * @param pfTest
     * @return
     */
    @PostMapping("/test")
    public ResponseEntity<?> userTestSave(@RequestBody PfTest pfTest) throws IOException {
        return userTestService.userTestSave(pfTest);
    }

    /**
     * test 등록
     *
     * @param
     * @return
     */
    @PostMapping("/test/file")
    public ResponseEntity<?> userTestFileSave(@ModelAttribute TestRequest testRequest) throws IOException {
        return userTestService.userTestFileSave(testRequest);
    }

    /**
     * test 업데이트
     *
     * @param testUpdateRequest
     * @return
     */
    /*
    @PutMapping("/test")
    public ResponseEntity<?> userTestUpdate(@RequestBody TestUpdateRequest testUpdateRequest) {
        return userTestService.userTestUpdate(testUpdateRequest);
    }
    */



}


