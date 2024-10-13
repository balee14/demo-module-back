package com.terry.demo.admin.controller;


import com.terry.demo.admin.domain.test.AdminTestService;
import com.terry.demo.admin.domain.test.dto.request.AdminTestRequest;
import com.terry.demo.admin.domain.test.dto.response.AdminTestDtoResponse;
import com.terry.demo.admin.domain.test.dto.response.AdminTestListResponse;
import com.terry.demo.core.dto.common.CommonResponseEntity;
import com.terry.demo.core.dto.common.CommonResponseEntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/admin/v1")
public class AdminTestController {

    /**
     * 사용자 관리
     */

    private final AdminTestService adminTestService;

    /**
     * admin 모든 test 목록 조회
     */
    @GetMapping("/test/tests")
    public ResponseEntity<?> getAdminTestList(@ModelAttribute Pageable pageable) {

        AdminTestListResponse adminTestListResponse = adminTestService.getAdminTestList(pageable);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, adminTestListResponse)
                , CommonResponseEntityType.OK.getHttpStatus());

    }

    /**
     * admin test 조회
     */
    @GetMapping("/test/testId/{testId}")
    public ResponseEntity<?> getAdminTestById(@PathVariable String testId) {

        AdminTestDtoResponse adminTestDtoResponse = adminTestService.getAdminTestById(testId);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, adminTestDtoResponse)
                , CommonResponseEntityType.OK.getHttpStatus());

    }

    /**
     * admin test 등록
     */
    @PostMapping("/test")
    public ResponseEntity<?> adminTestSave(@RequestBody AdminTestRequest adminTestRequest) {

        AdminTestDtoResponse adminTestDtoResponse = adminTestService.adminTestSave(adminTestRequest);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, adminTestDtoResponse)
                , CommonResponseEntityType.OK.getHttpStatus());

    }

    /**
     * admin test 파일 등록
     */
    @PostMapping("/test/file")
    public ResponseEntity<?> adminTestFileSave(@ModelAttribute AdminTestRequest adminTestRequest) throws IOException {

        AdminTestDtoResponse adminTestDtoResponse = adminTestService.adminTestFileSave(adminTestRequest);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, adminTestDtoResponse)
                , CommonResponseEntityType.OK.getHttpStatus());

    }

    /**
     * admin test 업데이트
     */
    @PutMapping("/test")
    public ResponseEntity<?> adminTestUpdate(@RequestBody AdminTestRequest adminTestRequest) {

        AdminTestDtoResponse adminTestDtoResponse = adminTestService.adminTestUpdate(adminTestRequest);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, adminTestDtoResponse)
                , CommonResponseEntityType.OK.getHttpStatus());
    }

}


