package com.terry.demo.domain.test;

import com.terry.demo.user.domain.test.UserTestService;
import com.terry.demo.user.domain.test.dto.response.UserTestDtoQueryResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserTestServiceTest {

    @Autowired
    UserTestService userTestService;

    @Test
    void getUserTestById() {

        //given
        String testId = "test-0HD5CMDTTMWPF";

        //when
        UserTestDtoQueryResponse userTestDtoQueryResponse = userTestService.getUserTestById(testId);

        //then
        assertNotNull(userTestDtoQueryResponse.getTestQueryDto());

    }

    @Test
    void userTestSave() {

        //given

        //when

        //then

    }

    @Test
    void userTestFileSave() {

        //given

        //when

        //then

    }

}