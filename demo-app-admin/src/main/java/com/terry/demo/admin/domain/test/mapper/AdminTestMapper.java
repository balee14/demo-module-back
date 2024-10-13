package com.terry.demo.admin.domain.test.mapper;

import com.terry.demo.admin.domain.test.dto.request.AdminTestRequest;
import com.terry.demo.core.dto.test.TestDto;
import com.terry.demo.core.entity.PfTest;
import org.springframework.util.ObjectUtils;

public class AdminTestMapper {

    /**
     *
     * @param adminTestRequest
     * @return
     */
    public static PfTest adminTestRequestToPfTest(AdminTestRequest adminTestRequest) {

        if (ObjectUtils.isEmpty(adminTestRequest)) {
            return null;
        }

        return  PfTest.builder()
                .testId(adminTestRequest.getTestId())
                .idEmail(adminTestRequest.getIdEmail())
                .description(adminTestRequest.getDescription())
                .build();

    }

    /**
     *
     * @param pfTest
     * @return
     */
    public static TestDto pfTestToAdminTestDto(PfTest pfTest) {

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
