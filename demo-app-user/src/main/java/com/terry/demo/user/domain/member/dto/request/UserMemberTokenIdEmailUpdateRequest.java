package com.terry.demo.user.domain.member.dto.request;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMemberTokenIdEmailUpdateRequest {

    private String idEmail;
    private String reAccessToken;
    private String refreshToken;

}

