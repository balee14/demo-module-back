package com.terry.demo.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberPwdUpdateRequest {

    private Long memberId;

    private String pwd;

    private String modId;



}
