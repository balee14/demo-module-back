package com.terry.demo.user.domain.test.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.terry.demo.core.dto.test.TestDto;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserTestDtoResponse {

    @JsonProperty("test")
    private TestDto testDto;

}

