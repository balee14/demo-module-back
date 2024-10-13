package com.terry.demo.user.domain.member.dto.request;


import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMemberListRequest {


    private String  memberTypeState; //회원 사용여부

    private String memberJoinType; //회원 가입 구분


}

