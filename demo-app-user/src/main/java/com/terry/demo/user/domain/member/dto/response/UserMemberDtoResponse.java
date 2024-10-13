package com.terry.demo.user.domain.member.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.terry.demo.core.dto.member.MemberDto;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMemberDtoResponse {

    @JsonProperty("member")
    private MemberDto memberDto;

}
