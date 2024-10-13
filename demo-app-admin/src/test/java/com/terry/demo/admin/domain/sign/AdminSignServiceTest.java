package com.terry.demo.admin.domain.sign;

import com.terry.demo.admin.domain.sign.dto.request.AdminSignRequest;
import com.terry.demo.admin.domain.sign.dto.response.AdminSignResponse;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Log4j2
@SpringBootTest
class AdminSignServiceTest {

    @Autowired
    AdminSignService adminSignService;

    @Test
    @DisplayName("로그인")
    void getAdminSignIn() {

        //given
        AdminSignRequest adminSignRequest = AdminSignRequest.builder()
                .idEmail("terry10@pomg.co.kr")
                .memberPwd("qwer!@1234")
                .build();

        //when
        AdminSignResponse adminSignResponse = adminSignService.getAdminSignIn(adminSignRequest);

        //then
        assertNotNull(adminSignResponse.getHeaders());
        log.info("getHeaders -> {}", adminSignResponse.getHeaders());

    }

    @Test
    void getAdminAccessToken() {
    }

    @Test
    void getAadminSignLogout() {
    }

}