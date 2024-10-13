package com.terry.demo.admin.domain.test.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.terry.demo.core.dto.test.TestDto;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminTestDtoResponse {

    @JsonProperty("test")
    private TestDto testDto;

}

