package com.terry.demo.admin.domain.member.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.terry.demo.core.dto.member.MemberQueryDto;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminMemberQueryDtoResponse {

    @JsonProperty("member")
    private MemberQueryDto memberQueryDto;

}
