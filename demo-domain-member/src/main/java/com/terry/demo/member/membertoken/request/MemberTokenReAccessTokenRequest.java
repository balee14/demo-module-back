package com.terry.demo.member.membertoken.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberTokenReAccessTokenRequest {

    private String idEmail;

    private String reAccessToken;

    private String refreshToken;

    private LocalDateTime accessTokenDt;

}

