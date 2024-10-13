package com.terry.demo.admin.domain.member;


import com.terry.demo.admin.domain.member.dto.request.AdminMemberListRequest;
import com.terry.demo.admin.domain.member.dto.request.AdminMemberRequest;
import com.terry.demo.admin.domain.member.dto.request.AdminMemberUpdateRequest;
import com.terry.demo.admin.domain.member.dto.response.AdminMemberCntResponse;
import com.terry.demo.admin.domain.member.dto.response.AdminMemberDtoResponse;
import com.terry.demo.admin.domain.member.dto.response.AdminMemberListResponse;
import com.terry.demo.admin.domain.member.dto.response.AdminMemberQueryDtoResponse;
import com.terry.demo.admin.domain.member.mapper.AdminMemberMapper;
import com.terry.demo.core.config.enums.EnumMapper;
import com.terry.demo.core.config.enums.EnumMapperValue;
import com.terry.demo.core.dto.common.CommonCustomType;
import com.terry.demo.core.dto.common.CommonRuntimeException;
import com.terry.demo.core.dto.member.MemberDto;
import com.terry.demo.core.dto.member.MemberQueryDto;
import com.terry.demo.core.dto.member.MembersQueryDto;
import com.terry.demo.core.entity.PfAuthority;
import com.terry.demo.core.entity.PfMember;
import com.terry.demo.core.entity.PfMemberRelationAuthority;
import com.terry.demo.core.enums.PfTsidEnum;
import com.terry.demo.core.util.PfTsidUtils;
import com.terry.demo.member.authority.repository.AuthorityRepository;
import com.terry.demo.member.member.repository.MemberRepository;
import com.terry.demo.member.member.request.MemberListRequest;
import com.terry.demo.member.member.request.MemberUpdateRequest;
import com.terry.demo.member.memberAuthority.repository.MemberAuthorityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class AdminMemberService {

    private static final Logger logger = LoggerFactory.getLogger(AdminMemberService.class);

    private final MemberRepository memberRepository;
    private final AuthorityRepository authorityRepository;
    private final MemberAuthorityRepository memberAuthorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final EnumMapper enumMapper;

    /**
     * 회원상태 enums 조회
     */
    public List<EnumMapperValue> getMemberStateEnums() {

        return enumMapper.get("MemberStateEnum");

    }

    /**
     * admin member 목록 조회
     */
    @Transactional(readOnly = true)
    public AdminMemberListResponse getAdminMemberList(AdminMemberListRequest adminMemberListRequest, Pageable pageable) {

        MemberListRequest memberListRequest = MemberListRequest.builder()
                .build();

        Page<MembersQueryDto> memberList = memberRepository.getMemberList(memberListRequest, pageable);

        return AdminMemberListResponse.builder()
                .membersQueryDtoList(memberList.getContent())
                .memberTotal(memberList.getTotalElements())
                .build();

    }

    /**
     * admin member 조회
     */
    @Transactional(readOnly = true)
    public AdminMemberQueryDtoResponse getAdminMemberById(String memberId) {

        MemberQueryDto memberQueryDto = memberRepository.getMemberById(memberId);

        return AdminMemberQueryDtoResponse.builder()
                .memberQueryDto(memberQueryDto)
                .build();

    }

    /**
     * admin member 등록
     */
    public AdminMemberDtoResponse adminMemberSave(AdminMemberRequest adminMemberRequest) {

        // 유저 등록

        // 유저체크
        if (memberRepository.findByIdEmail(adminMemberRequest.getIdEmail()).orElse(null) != null) {
            throw new CommonRuntimeException(CommonCustomType.SUCCESSFUL_200_2.name());
        }

        adminMemberRequest.setMemberId(PfTsidUtils.tsidStr(PfTsidEnum.MEMBER.getTsidId()));
        // 패스워드 암호화
        adminMemberRequest.setMemberPwd(passwordEncoder.encode(adminMemberRequest.getMemberPwd()));
        PfMember pfMember = AdminMemberMapper.adminMemberRequestToPfMember(adminMemberRequest);
        PfMember pfMemberSave = memberRepository.save(Objects.requireNonNull(pfMember));

        // 권한 설정
        PfAuthority pfAuthority = null;

        String authorityName = "ROLE_ADMIN";
        Optional<PfAuthority> pfAuthorityOptional = authorityRepository.findByAuthorityName(authorityName);
        if (pfAuthorityOptional.isPresent()) {
            pfAuthority = pfAuthorityOptional.get();
        } else {
            PfAuthority pfAuthorityBuilder = PfAuthority.builder()
                    .authorityId(PfTsidUtils.tsidStr(PfTsidEnum.AUTHORITY.getTsidId()))
                    .authorityName(authorityName)
                    .build();
            pfAuthority = authorityRepository.save(pfAuthorityBuilder);
        }

        // 유저, 권한 매핑
        PfMemberRelationAuthority pfMemberRelationAuthority = PfMemberRelationAuthority.builder()
                .id(PfTsidUtils.tsidStr(PfTsidEnum.MEMBER_AUTHORITY.getTsidId()))
                .pfMember(pfMemberSave)
                .pfAuthority(pfAuthority)
                .build();
        memberAuthorityRepository.save(pfMemberRelationAuthority);

        MemberDto memberDto = AdminMemberMapper.pfMemberToAdminMemberDto(pfMemberSave);

        return AdminMemberDtoResponse.builder()
                .memberDto(memberDto)
                .build();

    }

    /**
     * admin member 수정
     */
    public AdminMemberCntResponse adminMemberUpdate(AdminMemberUpdateRequest adminMemberUpdateRequest) {

        MemberUpdateRequest memberUpdateRequest = MemberUpdateRequest.builder()
                .memberId(adminMemberUpdateRequest.getMemberId())
                .memberName(adminMemberUpdateRequest.getMemberName())
                .memberMobilePhone(adminMemberUpdateRequest.getMemberMobilePhone())
                .build();

        long memberCnt = memberRepository.memberUpdate(memberUpdateRequest);

        return AdminMemberCntResponse.builder()
                .memberCnt(memberCnt)
                .build();

    }

}

