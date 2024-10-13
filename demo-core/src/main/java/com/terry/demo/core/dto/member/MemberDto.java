package com.terry.demo.core.dto.member;

import com.terry.demo.core.entity.PfAuthority;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDto {

    private String memberId;

    private String idEmail;

    private String memberPwd;

    private String memberName;

    private String memberNickName;

    private String memberEmail;

    private String memberMobilePhone;

    private String memberState;

    private List<PfAuthority> authorities;

}

