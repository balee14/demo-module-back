package com.terry.demo.batch.domain.test.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.terry.demo.core.dto.member.MemberDto;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BatchTestDtoResponse {

    @JsonProperty("member")
    private MemberDto memberDto;

}
