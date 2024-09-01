package com.terry.demo.controller;

import com.terry.demo.core.entity.PfTest;
import com.terry.demo.test.dto.TestListRequest;
import com.terry.demo.test.dto.TestRequest;
import com.terry.demo.service.test.AdminTestService;
import com.terry.demo.test.dto.TestUpdateRequest;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/admin/v1")
public class AdminTestController {

    /**
     * 테스트 관리
     */

    private final AdminTestService adminTestService;

    /**
     * 모든 test 목록 조회
     *
     * @param
     * @return
     */
    @GetMapping("/test/tests")
    public ResponseEntity<?> getAdminTestList(@ModelAttribute TestListRequest testListRequest, Pageable pageable) {
        return adminTestService.getAdminTestList(testListRequest, pageable);
    }

    /**
     * test 조회
     *
     * @param
     * @return
     */
    @GetMapping("/test/testId/{testId}")
    public ResponseEntity<?> getAdminTestById(@PathVariable("testId") Long testId) {
        return adminTestService.getAdminTestById(testId);
    }

    /**
     * test 등록
     *
     * @param pfTest
     * @return
     */
    @PostMapping("/test")
    public ResponseEntity<?> adminTestSave(@RequestBody PfTest pfTest) throws IOException {
        return adminTestService.adminTestSave(pfTest);
    }

    /**
     * test 업데이트
     *
     * @param testUpdateRequest
     * @return
     */
    @PutMapping("/test")
    public ResponseEntity<?> adminTestUpdate(@RequestBody TestUpdateRequest testUpdateRequest) {
        return adminTestService.adminTestUpdate(testUpdateRequest);
    }



}


