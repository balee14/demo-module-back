package com.terry.demo.service.member;

import com.terry.demo.core.config.enums.EnumMapper;
import com.terry.demo.core.config.enums.EnumMapperValue;
import com.terry.demo.core.dto.CommonResponseEntity;
import com.terry.demo.core.dto.CommonResponseEntityType;
import com.terry.demo.core.entity.PfMember;
import com.terry.demo.member.dto.MemberDto;
import com.terry.demo.member.dto.MemberPwdUpdateRequest;
import com.terry.demo.member.dto.MemberResponse.MemberDtoResponse;
import com.terry.demo.member.dto.MemberResponse.MemberEntityResponse;
import com.terry.demo.member.dto.MemberResponse.MemberUpdateResponse;
import com.terry.demo.member.dto.MemberUpdateRequest;
import com.terry.demo.member.dto.MemberListRequest;
import com.terry.demo.member.dto.MemberListResponse;
import com.terry.demo.member.dto.MembersDto;
import com.terry.demo.member.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class AdminMemberService {

    private final MemberService memberService;
    private final EnumMapper enumMapper;

    /**
     * member 회원 상태
     * @return
     */
    public ResponseEntity<?> getMemberStateTypes() {
        List<EnumMapperValue> memberStateList =  enumMapper.get("MemberStateEnum");
        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, memberStateList)
                , CommonResponseEntityType.OK.getHttpStatus());
    }

    /**
     * admin member 목록 조회
     * @param
     * @return
     */
    @Transactional(readOnly = true)
    public ResponseEntity<?> getAdminMemberList(MemberListRequest memberListRequest, Pageable pageable) {

        MemberListResponse memberListResponse = new MemberListResponse();

        Page<MembersDto> memberList = memberService.getMemberList(memberListRequest, pageable);

        memberListResponse.setMemberList(memberList.getContent());
        memberListResponse.setAllCount(memberList.getTotalElements());

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, memberListResponse)
            , CommonResponseEntityType.OK.getHttpStatus());

    }

    /**
     * admin member 조회
     * @param
     * @return
     */
    @Transactional(readOnly = true)
    public ResponseEntity<?> getAdminMemberById(Long memberId) {
        MemberDtoResponse memberDtoResponse  = new MemberDtoResponse();

        MemberDto memberDto = memberService.getMemberById(memberId);
        memberDtoResponse.setMember(memberDto);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, memberDtoResponse)
            , CommonResponseEntityType.OK.getHttpStatus());

    }

    /**
     * 등록
     * @param pfMember
     * @return
     */
    public ResponseEntity<?> adminMemberSave(PfMember pfMember) {

        MemberEntityResponse memberEntityResponse = new MemberEntityResponse();

        PfMember pfMemberSave = memberService.memberSave(pfMember);
        memberEntityResponse.setMember(pfMemberSave);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, memberEntityResponse)
            , CommonResponseEntityType.OK.getHttpStatus());

    }

    /**
     * 수정
     * @param memberUpdateRequest
     * @return
     */
    public ResponseEntity<?> adminMemberUpdate(MemberUpdateRequest memberUpdateRequest) {

        MemberUpdateResponse memberUpdateResponse = new MemberUpdateResponse();

        Long memberCnt = memberService.memberUpdate(memberUpdateRequest);
        memberUpdateResponse.setMemberCnt(memberCnt);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, memberUpdateResponse)
            , CommonResponseEntityType.OK.getHttpStatus());

    }

    /**
     * 수정
     * @param memberPwdUpdateRequest
     * @return
     */
    public ResponseEntity<?> adminMemberPwdUpdate(MemberPwdUpdateRequest memberPwdUpdateRequest) {

        MemberUpdateResponse memberUpdateResponse = new MemberUpdateResponse();

        Long memberCnt = memberService.memberPwdUpdate(memberPwdUpdateRequest);
        memberUpdateResponse.setMemberCnt(memberCnt);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, memberUpdateResponse)
            , CommonResponseEntityType.OK.getHttpStatus());

    }



}

