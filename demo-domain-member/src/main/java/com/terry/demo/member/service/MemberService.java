package com.terry.demo.member.service;

import com.terry.demo.core.dto.CommonCustomType;
import com.terry.demo.core.dto.CommonRuntimeException;
import com.terry.demo.core.entity.PfAuthority;
import com.terry.demo.core.entity.PfMember;
import com.terry.demo.core.util.PfMemberUtil;
import com.terry.demo.member.dto.MemberDto;
import com.terry.demo.member.dto.MemberPwdUpdateRequest;
import com.terry.demo.member.dto.MemberUpdateRequest;
import com.terry.demo.member.dto.MemberListRequest;
import com.terry.demo.member.dto.MembersDto;
import com.terry.demo.member.repository.MemberRepository;
import com.terry.demo.member.repository.MemberRepositoryCustom;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberRepositoryCustom memberRepositoryCustom;
    private final PasswordEncoder passwordEncoder;

    /**
     * member 목록 조회
     *
     * @param
     * @return
     */
    @Transactional(readOnly = true)
    public Page<MembersDto> getMemberList(MemberListRequest memberListRequest, Pageable pageable) {
        return memberRepositoryCustom.getMemberList(memberListRequest, pageable);
    }

    /**
     * member 조회
     * @param
     * @return
     */
    @Transactional(readOnly = true)
    public MemberDto getMemberById(Long memberId) {
        return memberRepositoryCustom.getMemberById(memberId);
    }

    /**
     *
     * @param kpMember
     * @return
     */
    public PfMember memberSave(PfMember kpMember) {

        // 유저체크
        if (memberRepository.findByIdEmail(kpMember.getIdEmail()).orElse(null) != null) {
            throw new CommonRuntimeException(CommonCustomType.SUCCESSFUL_200_2.name());
        }

        // 패스워드 암호화
        kpMember.setPwd(passwordEncoder.encode(kpMember.getPwd()));

        // 권한 설정
        PfAuthority authority;
        // 어드민 사용자
        authority = PfAuthority.builder()
            .authorityName("ROLE_ADMIN")
            .build();
//        if (kpMember.getIsMemberAdmin()) {
//            // 어드민 사용자
//            authority = PfAuthority.builder()
//                .authorityName("ROLE_ADMIN")
//                .build();
//        } else {
//            // 파트너사 사용자
//            authority = PfAuthority.builder()
//                .authorityName("ROLE_PARTNER")
//                .build();
//        }
        kpMember.setAuthorities(Collections.singleton(authority));
        return memberRepository.save(kpMember);

    }

    /**
     *
     * @param memberUpdateRequest
     * @return
     */
    public Long memberUpdate(MemberUpdateRequest memberUpdateRequest) {

        memberUpdateRequest.setModId(PfMemberUtil.getIdEmail());
        return memberRepositoryCustom.memberUpdate(memberUpdateRequest);

    }

    /**
     *
     * @param memberPwdUpdateRequest
     * @return
     */
    public Long memberPwdUpdate(MemberPwdUpdateRequest memberPwdUpdateRequest) {

        // 패스워드 암호화
        memberPwdUpdateRequest.setPwd(passwordEncoder.encode(memberPwdUpdateRequest.getPwd()));
        memberPwdUpdateRequest.setModId(PfMemberUtil.getIdEmail());
        return memberRepositoryCustom.memberPwdUpdate(memberPwdUpdateRequest);

    }



}

