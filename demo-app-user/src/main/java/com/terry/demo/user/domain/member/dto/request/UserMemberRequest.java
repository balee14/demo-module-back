package com.terry.demo.user.domain.member.dto.request;

import com.terry.demo.core.enums.PfMemberStateEnum;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMemberRequest {

    // @Comment("ID")
    @Setter
    private String memberId;

    // @Comment("사용자ID")
    private String idEmail;

    // @Comment("회원PW")
    @Setter
    private String memberPwd;

    // @Comment("이름")
    private String memberName;

    // @Comment("닉네임")
    private String memberNickName;

    // @Comment("대표이메일")
    private String memberEmail;

    // @Comment("휴대폰")
    private String memberMobilePhone;

    // @Comment("사용여부(사용:Y, 미사용:N)")
    private PfMemberStateEnum memberState;

    // @Comment("최종접속시간")
    //private LocalDateTime lastConnection;

    // 권한
//    private Set<PfAuthority> authorities;


}

