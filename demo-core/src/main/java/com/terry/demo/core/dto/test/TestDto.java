package com.terry.demo.core.dto.test;


import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TestDto {

    private String testId;

    private String idEmail;

    private String description;

}

