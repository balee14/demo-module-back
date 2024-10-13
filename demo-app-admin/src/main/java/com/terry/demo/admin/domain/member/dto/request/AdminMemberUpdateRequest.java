package com.terry.demo.admin.domain.member.dto.request;

import com.terry.demo.core.enums.PfMemberStateEnum;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminMemberUpdateRequest {

    private String memberId;

    private String memberPwd;

    private String memberName;

    private String memberNickName;

    private String memberEmail;

    private String memberMobilePhone;

    private PfMemberStateEnum memberState;

}
