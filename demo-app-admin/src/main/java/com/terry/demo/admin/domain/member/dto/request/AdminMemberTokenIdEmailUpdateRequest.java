package com.terry.demo.admin.domain.member.dto.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminMemberTokenIdEmailUpdateRequest {

    private String idEmail;

    private String reAccessToken;

    private String refreshToken;

}

