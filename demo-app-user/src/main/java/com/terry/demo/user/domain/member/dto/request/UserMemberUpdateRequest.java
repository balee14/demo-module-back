package com.terry.demo.user.domain.member.dto.request;

import com.terry.demo.core.enums.PfMemberStateEnum;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMemberUpdateRequest {

    private String memberId;

    private String memberPwd;

    private String memberName;

    private String memberNickName;

    private String memberEmail;

    private String memberMobilePhone;

    private PfMemberStateEnum memberState;

}
