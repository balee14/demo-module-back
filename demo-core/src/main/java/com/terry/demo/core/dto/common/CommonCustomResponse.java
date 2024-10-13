package com.terry.demo.core.dto.common;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonCustomResponse {

    private String customCode;
    private String customMessage;

}
