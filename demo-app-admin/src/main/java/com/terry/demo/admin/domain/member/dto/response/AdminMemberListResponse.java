package com.terry.demo.admin.domain.member.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.terry.demo.core.dto.member.MembersQueryDto;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminMemberListResponse {

    @JsonProperty("memberList")
    private List<MembersQueryDto> membersQueryDtoList;

    private long memberTotal;

}

