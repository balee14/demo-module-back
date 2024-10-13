package com.terry.demo.user.domain.test.mapper;

import com.terry.demo.core.dto.test.TestDto;
import com.terry.demo.core.entity.PfTest;
import com.terry.demo.user.domain.test.dto.request.UserTestRequest;
import org.springframework.util.ObjectUtils;

public class UserTestTo {

    /**
     *
     * @param userTestRequest
     * @return
     */
    public static PfTest userTestRequestToPfTest(UserTestRequest userTestRequest) {

        if (ObjectUtils.isEmpty(userTestRequest)) {
            return null;
        }

        return  PfTest.builder()
                .testId(userTestRequest.getTestId())
                .idEmail(userTestRequest.getIdEmail())
                .description(userTestRequest.getDescription())
                .build();

    }

    /**
     *
     * @param pfTest
     * @return
     */
    public static TestDto pfTestToUserTestDto(PfTest pfTest) {

        if (ObjectUtils.isEmpty(pfTest)) {
            return null;
        }

        return  TestDto.builder()
                .testId(pfTest.getTestId())
                .idEmail(pfTest.getIdEmail())
                .description(pfTest.getDescription())
                .build();

    }


}
