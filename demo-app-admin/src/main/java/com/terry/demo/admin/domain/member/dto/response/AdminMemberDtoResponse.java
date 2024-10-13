package com.terry.demo.admin.domain.member.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.terry.demo.core.dto.member.MemberDto;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminMemberDtoResponse {

    @JsonProperty("member")
    private MemberDto memberDto;

}
