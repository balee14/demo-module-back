package com.terry.demo.admin.domain.member.dto.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminMemberListRequest {


    private String  memberTypeState; //회원 사용여부

    private String memberJoinType; //회원 가입 구분


}

