package com.terry.demo.core.dto.membertoken;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberTokenIdEmailUpdateDto {

    private String idEmail;

    private String reAccessToken;

    private String refreshToken;

}

