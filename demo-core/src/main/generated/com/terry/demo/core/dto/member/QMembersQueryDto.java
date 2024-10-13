package com.terry.demo.core.dto.member;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.terry.demo.core.dto.member.QMembersQueryDto is a Querydsl Projection type for MembersQueryDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMembersQueryDto extends ConstructorExpression<MembersQueryDto> {

    private static final long serialVersionUID = 424373218L;

    public QMembersQueryDto(com.querydsl.core.types.Expression<String> memberId, com.querydsl.core.types.Expression<String> idEmail, com.querydsl.core.types.Expression<String> memberName, com.querydsl.core.types.Expression<String> memberNickName, com.querydsl.core.types.Expression<String> memberEmail, com.querydsl.core.types.Expression<String> memberMobilePhone, com.querydsl.core.types.Expression<String> memberState) {
        super(MembersQueryDto.class, new Class<?>[]{String.class, String.class, String.class, String.class, String.class, String.class, String.class}, memberId, idEmail, memberName, memberNickName, memberEmail, memberMobilePhone, memberState);
    }

}

