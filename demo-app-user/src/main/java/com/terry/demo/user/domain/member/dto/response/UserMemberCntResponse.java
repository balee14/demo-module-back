package com.terry.demo.user.domain.member.dto.response;



import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMemberCntResponse {

    private long memberCnt;

}

