package com.terry.demo.member.membertoken.request;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberTokenErrorRequest {

    private String accessToken;

    private String refreshToken;

    private String idEmail;

    private String modId;

}

