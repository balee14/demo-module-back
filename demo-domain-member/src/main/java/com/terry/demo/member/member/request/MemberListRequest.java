package com.terry.demo.member.member.request;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberListRequest {

    private String  memberTypeState; //회원 사용여부

    private String memberJoinType; //회원 가입 구분

}

