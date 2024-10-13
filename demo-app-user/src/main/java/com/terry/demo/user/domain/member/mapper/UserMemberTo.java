package com.terry.demo.user.domain.member.mapper;

import com.terry.demo.core.dto.member.MemberDto;
import com.terry.demo.core.entity.PfMember;
import com.terry.demo.user.domain.member.dto.request.UserMemberRequest;
import org.springframework.util.ObjectUtils;

public class UserMemberTo {

    /**
     *
     * @param userMemberRequest
     * @return
     */
    public static PfMember userMemberRequestToPfMember(UserMemberRequest userMemberRequest) {

        if (ObjectUtils.isEmpty(userMemberRequest)) {
            return null;
        }

        return  PfMember.builder()
                .memberId(userMemberRequest.getMemberId())
                .idEmail(userMemberRequest.getIdEmail())
                .memberPwd(userMemberRequest.getMemberPwd())
                .memberName(userMemberRequest.getMemberName())
                .memberNickName(userMemberRequest.getMemberNickName())
                .memberEmail(userMemberRequest.getMemberEmail())
                .memberMobilePhone(userMemberRequest.getMemberMobilePhone())
                .memberState(userMemberRequest.getMemberState())
//                .authorities(userMemberRequest.getAuthorities())
                .build();

    }

    /**
     *
     * @param pfMember
     * @return
     */
    public static MemberDto pfMemberToUserMemberDto(PfMember pfMember) {

        if (ObjectUtils.isEmpty(pfMember)) {
            return null;
        }

        return  MemberDto.builder()
                .memberId(pfMember.getMemberId())
                .idEmail(pfMember.getIdEmail())
                .memberPwd(pfMember.getMemberPwd())
                .memberName(pfMember.getMemberName())
                .memberNickName(pfMember.getMemberNickName())
                .memberEmail(pfMember.getMemberEmail())
                .memberMobilePhone(pfMember.getMemberMobilePhone())
                .memberState(pfMember.getMemberState().name())
//                .authorities(pfMember.getAuthorities().stream().toList())
                .build();

    }


}
