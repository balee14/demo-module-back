package com.terry.demo.core.dto.member;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MembersQueryDto {

    private String memberId;

    private String idEmail;

    private String memberName;

    private String memberNickName;

    private String memberEmail;

    private String memberMobilePhone;

    private String memberState;

    @QueryProjection
    public MembersQueryDto(String memberId,
                           String idEmail,
                           String memberName,
                           String memberNickName,
                           String memberEmail,
                           String memberMobilePhone,
                           String memberState) {
        this.memberId = memberId;
        this.idEmail = idEmail;
        this.memberName = memberName;
        this.memberNickName = memberNickName;
        this.memberEmail = memberEmail;
        this.memberMobilePhone = memberMobilePhone;
        this.memberState = memberState;
    }

}

