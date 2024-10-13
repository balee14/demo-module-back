package com.terry.demo.admin.domain.member.mapper;

import com.terry.demo.admin.domain.member.dto.request.AdminMemberRequest;
import com.terry.demo.core.dto.member.MemberDto;
import com.terry.demo.core.entity.PfMember;
import org.springframework.util.ObjectUtils;

public class AdminMemberMapper {

    /**
     *
     * @param adminMemberRequest
     * @return
     */
    public static PfMember adminMemberRequestToPfMember(AdminMemberRequest adminMemberRequest) {

        if (ObjectUtils.isEmpty(adminMemberRequest)) {
            return null;
        }

        return  PfMember.builder()
                .memberId(adminMemberRequest.getMemberId())
                .idEmail(adminMemberRequest.getIdEmail())
                .memberPwd(adminMemberRequest.getMemberPwd())
                .memberName(adminMemberRequest.getMemberName())
                .memberNickName(adminMemberRequest.getMemberNickName())
                .memberEmail(adminMemberRequest.getMemberEmail())
                .memberMobilePhone(adminMemberRequest.getMemberMobilePhone())
                .memberState(adminMemberRequest.getMemberState())
//                .authorities(adminMemberRequest.getAuthorities())
                .build();

    }

    /**
     *
     * @param pfMember
     * @return
     */
    public static MemberDto pfMemberToAdminMemberDto(PfMember pfMember) {

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
