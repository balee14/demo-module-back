package com.terry.demo.admin.domain.test.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.terry.demo.core.entity.PfTest;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminTestEntityResponse {

    @JsonProperty("test")
    private PfTest pfTest;

}

