package com.terry.demo.member.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.terry.demo.core.enums.MemberStateEnum;

public record MemberDto(Long memberId
    ,   String idEmail
    ,   MemberStateEnum state
    ,   String name) {

    @QueryProjection
    public MemberDto(Long memberId, String idEmail
                , MemberStateEnum state, String name) {
        this.memberId = memberId;
        this.idEmail = idEmail;
        this.state = state;
        this.name = name;
    }

}


