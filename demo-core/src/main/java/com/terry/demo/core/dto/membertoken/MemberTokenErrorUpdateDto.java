package com.terry.demo.core.dto.membertoken;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberTokenErrorUpdateDto {

    private String accessToken;

    private String refreshToken;

    private String idEmail;

    private String modId;

}

