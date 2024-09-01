package com.terry.demo.member.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.terry.demo.member.dto.QMemberDto is a Querydsl Projection type for MemberDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMemberDto extends ConstructorExpression<MemberDto> {

    private static final long serialVersionUID = 833531290L;

    public QMemberDto(com.querydsl.core.types.Expression<Long> memberId, com.querydsl.core.types.Expression<String> idEmail, com.querydsl.core.types.Expression<com.terry.demo.core.enums.MemberStateEnum> state, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> telephone, com.querydsl.core.types.Expression<String> email, com.querydsl.core.types.Expression<Long> point, com.querydsl.core.types.Expression<Long> orderCount, com.querydsl.core.types.Expression<Long> reviewCount, com.querydsl.core.types.Expression<Boolean> isContractAgree, com.querydsl.core.types.Expression<Boolean> isMarketingAgree, com.querydsl.core.types.Expression<java.time.LocalDateTime> lastConnection) {
        super(MemberDto.class, new Class<?>[]{long.class, String.class, com.terry.demo.core.enums.MemberStateEnum.class, String.class, String.class, String.class, long.class, long.class, long.class, boolean.class, boolean.class, java.time.LocalDateTime.class}, memberId, idEmail, state, name, telephone, email, point, orderCount, reviewCount, isContractAgree, isMarketingAgree, lastConnection);
    }

}

