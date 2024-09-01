package com.terry.demo.membertoken.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberTokenErrorUpdate {

    private String accessToken;
    private String refreshToken;
    private String idEmail;
    private String modId;

}

