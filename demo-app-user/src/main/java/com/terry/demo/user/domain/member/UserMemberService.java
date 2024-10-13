package com.terry.demo.user.domain.member;

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
import com.terry.demo.user.domain.member.dto.request.UserMemberListRequest;
import com.terry.demo.user.domain.member.dto.request.UserMemberRequest;
import com.terry.demo.user.domain.member.dto.request.UserMemberUpdateRequest;
import com.terry.demo.user.domain.member.dto.response.UserMemberCntResponse;
import com.terry.demo.user.domain.member.dto.response.UserMemberDtoResponse;
import com.terry.demo.user.domain.member.dto.response.UserMemberListResponse;
import com.terry.demo.user.domain.member.dto.response.UserMemberQueryDtoResponse;
import com.terry.demo.user.domain.member.mapper.UserMemberTo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;


@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class UserMemberService {

    private static final Logger logger = LoggerFactory.getLogger(UserMemberService.class);

    private final MemberRepository memberRepository;
    private final AuthorityRepository authorityRepository;
    private final MemberAuthorityRepository memberAuthorityRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * user member 목록 조회
     */
    @Transactional(readOnly = true)
    public UserMemberListResponse getUserMemberList(UserMemberListRequest userMemberListRequest, Pageable pageable) {

        MemberListRequest memberListRequest = MemberListRequest.builder()
                .memberTypeState(userMemberListRequest.getMemberTypeState())
                .memberJoinType(userMemberListRequest.getMemberJoinType())
                .build();

        Page<MembersQueryDto> memberList = memberRepository.getMemberList(memberListRequest, pageable);

        return UserMemberListResponse.builder()
                .membersQueryDtoList(memberList.getContent())
                .memberTotal(memberList.getTotalElements())
                .build();

    }

    /**
     * user member 조회
     */
    @Transactional(readOnly = true)
    public UserMemberQueryDtoResponse getUserMemberById(String memberId) {

        MemberQueryDto memberQueryDto = memberRepository.getMemberById(memberId);

        return UserMemberQueryDtoResponse.builder()
                .memberQueryDto(memberQueryDto)
                .build();

    }

    /**
     * user member 등록
     */
    public UserMemberDtoResponse userMemberSave(UserMemberRequest userMemberRequest) {

        // 유저 등록

        // 유저체크
        if (memberRepository.findByIdEmail(userMemberRequest.getIdEmail()).orElse(null) != null) {
            throw new CommonRuntimeException(CommonCustomType.SUCCESSFUL_200_2.name());
        }

        userMemberRequest.setMemberId(PfTsidUtils.tsidStr(PfTsidEnum.MEMBER.getTsidId()));
        // 패스워드 암호화
        userMemberRequest.setMemberPwd(passwordEncoder.encode(userMemberRequest.getMemberPwd()));
        PfMember pfMember = UserMemberTo.userMemberRequestToPfMember(userMemberRequest);
        PfMember pfMemberSave = memberRepository.save(Objects.requireNonNull(pfMember));

        // 권한 설정
        PfAuthority pfAuthority = null;

        String authorityName = "ROLE_USER";
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

        MemberDto memberDto = UserMemberTo.pfMemberToUserMemberDto(pfMemberSave);

        return UserMemberDtoResponse.builder()
                .memberDto(memberDto)
                .build();

    }

    /**
     * user member 수정
     */
    public UserMemberCntResponse userMemberUpdate(UserMemberUpdateRequest userMemberUpdateRequest) {

        MemberUpdateRequest memberUpdateRequest = MemberUpdateRequest.builder()
                .memberId(userMemberUpdateRequest.getMemberId())
                .memberPwd(userMemberUpdateRequest.getMemberPwd())
                .memberName(userMemberUpdateRequest.getMemberName())
                .memberNickName(userMemberUpdateRequest.getMemberNickName())
                .memberEmail(userMemberUpdateRequest.getMemberEmail())
                .memberMobilePhone(userMemberUpdateRequest.getMemberMobilePhone())
                .memberState(userMemberUpdateRequest.getMemberState())
                .build();
        long memberCnt = memberRepository.memberUpdate(memberUpdateRequest);

        return UserMemberCntResponse.builder()
                .memberCnt(memberCnt)
                .build();

    }

}

