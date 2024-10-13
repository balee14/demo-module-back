package com.terry.demo.core.dto.test;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestQueryDto {

    private String testId;

    private String idEmail;

    @QueryProjection
    public TestQueryDto(String testId, String idEmail) {
        this.testId = testId;
        this.idEmail = idEmail;
    }

}

