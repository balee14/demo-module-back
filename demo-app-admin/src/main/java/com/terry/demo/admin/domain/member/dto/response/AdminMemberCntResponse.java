package com.terry.demo.admin.domain.member.dto.response;


import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminMemberCntResponse {

    private long memberCnt;

}

