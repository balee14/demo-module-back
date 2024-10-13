package com.terry.demo.core.dto.test;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.terry.demo.core.dto.test.QTestQueryDto is a Querydsl Projection type for TestQueryDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QTestQueryDto extends ConstructorExpression<TestQueryDto> {

    private static final long serialVersionUID = -752430145L;

    public QTestQueryDto(com.querydsl.core.types.Expression<String> testId, com.querydsl.core.types.Expression<String> idEmail) {
        super(TestQueryDto.class, new Class<?>[]{String.class, String.class}, testId, idEmail);
    }

}

