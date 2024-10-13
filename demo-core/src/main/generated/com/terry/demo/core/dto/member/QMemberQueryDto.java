package com.terry.demo.core.dto.member;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.terry.demo.core.dto.member.QMemberQueryDto is a Querydsl Projection type for MemberQueryDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMemberQueryDto extends ConstructorExpression<MemberQueryDto> {

    private static final long serialVersionUID = 110382783L;

    public QMemberQueryDto(com.querydsl.core.types.Expression<String> memberId, com.querydsl.core.types.Expression<String> idEmail, com.querydsl.core.types.Expression<String> memberName, com.querydsl.core.types.Expression<String> memberNickName, com.querydsl.core.types.Expression<String> memberEmail, com.querydsl.core.types.Expression<String> memberMobilePhone, com.querydsl.core.types.Expression<String> memberState) {
        super(MemberQueryDto.class, new Class<?>[]{String.class, String.class, String.class, String.class, String.class, String.class, String.class}, memberId, idEmail, memberName, memberNickName, memberEmail, memberMobilePhone, memberState);
    }

    public QMemberQueryDto(com.querydsl.core.types.Expression<String> memberId, com.querydsl.core.types.Expression<String> idEmail, com.querydsl.core.types.Expression<String> memberName) {
        super(MemberQueryDto.class, new Class<?>[]{String.class, String.class, String.class}, memberId, idEmail, memberName);
    }

}

