package com.terry.demo.admin.domain.test;

import com.terry.demo.admin.domain.test.dto.request.AdminTestRequest;
import com.terry.demo.admin.domain.test.dto.response.AdminTestDtoResponse;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Log4j2
@SpringBootTest
class AdminTestServiceTest {

    @Autowired
    AdminTestService adminTestService;

    @Test
    @DisplayName("테스트 전체 조회")
    void getAdminTestList() {
        /*
        //given

        //when
        AdminTestListResponse adminTestListResponse = adminTestService.getAdminTestList(Pageable pageable);

        //then
        assertNotNull(adminTestListResponse.getTestDtoList());
        log.info("size is -> {}", adminTestListResponse.getTestDtoList().size());
        */
    }

    @Test
    @DisplayName("테스트 단일 조회")
    void getAdminTestById() {

        //given
        String testId = "test-0HD5CMSJKMBQV";

        //when
        AdminTestDtoResponse adminTestDtoResponse = adminTestService.getAdminTestById(testId);

        //then
        assertNotNull(adminTestDtoResponse.getTestDto());
        log.info("pfTest is -> {}", adminTestDtoResponse.getTestDto());

    }

    @Test
    void adminTestSave() {

        //given
        AdminTestRequest adminTestRequest = AdminTestRequest.builder()
                .idEmail("test5")
                .build();

        //when
        AdminTestDtoResponse adminTestDtoResponse = adminTestService.adminTestSave(adminTestRequest);

        //then
        assertNotNull(adminTestDtoResponse.getTestDto());

    }

    @Test
    void adminTestFileSave() {

    }

    @Test
    void adminTestUpdate() {

    }

}