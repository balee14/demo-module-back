package com.terry.demo.user.domain.sign.dto.request;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSignRequest {

    private String idEmail;

    private String memberPwd;

}
