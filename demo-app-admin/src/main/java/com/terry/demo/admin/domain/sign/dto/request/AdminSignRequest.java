package com.terry.demo.admin.domain.sign.dto.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminSignRequest {

    private String idEmail;

    private String memberPwd;
}
