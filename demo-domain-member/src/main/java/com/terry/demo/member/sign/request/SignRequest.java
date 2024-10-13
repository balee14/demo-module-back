package com.terry.demo.member.sign.request;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignRequest {

    private String idEmail;

    private String memberPwd;

}
