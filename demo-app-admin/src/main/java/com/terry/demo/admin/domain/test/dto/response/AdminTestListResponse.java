package com.terry.demo.admin.domain.test.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.terry.demo.core.dto.test.TestDto;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminTestListResponse {

    @JsonProperty("testList")
    private List<TestDto> testDtoList;

}

