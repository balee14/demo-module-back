package com.terry.demo.controller;

import com.terry.demo.core.entity.PfMember;
import com.terry.demo.member.dto.MemberPwdUpdateRequest;
import com.terry.demo.member.dto.MemberUpdateRequest;
import com.terry.demo.member.dto.MemberListRequest;
import com.terry.demo.service.member.AdminMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/admin/v1")
public class AdminMemberController {

    /**
     * 사용자 관리
     */

    private final AdminMemberService adminMemberService;

    /**
     * member 회원 상태 목록 조회
     *
     * @param
     * @return
     */
    @GetMapping("/member/memberStateTypes")
    public ResponseEntity<?> getMemberStateTypes() {
        return adminMemberService.getMemberStateTypes();
    }

    /**
     * 모든 member 목록 조회
     *
     * @param
     * @return
     */
    @GetMapping("/member/members")
    public ResponseEntity<?> getAdminMemberList(@ModelAttribute MemberListRequest memberListRequest, Pageable pageable) {
        return adminMemberService.getAdminMemberList(memberListRequest, pageable);
    }

    /**
     * member 조회
     *
     * @param
     * @return
     */
    @GetMapping("/member/memberId/{memberId}")
    public ResponseEntity<?> getAdminMemberById(@PathVariable("memberId") Long memberId) {
        return adminMemberService.getAdminMemberById(memberId);
    }

    /**
     * member 등록
     *
     * @param pfMember
     * @return
     */
    @PostMapping("/member")
    public ResponseEntity<?> adminMemberSave(@RequestBody PfMember pfMember) {
        return adminMemberService.adminMemberSave(pfMember);
    }

    /**
     * member 업데이트
     *
     * @param memberUpdateRequest
     * @return
     */
    @PutMapping("/member")
    public ResponseEntity<?> adminMemberUpdate(@RequestBody MemberUpdateRequest memberUpdateRequest) {
        return adminMemberService.adminMemberUpdate(memberUpdateRequest);
    }

    /**
     * member 비밀번호 업데이트
     *
     * @param memberPwdUpdateRequest
     * @return
     */
    @PatchMapping("/member/pwd")
    public ResponseEntity<?> adminMemberPwdUpdate(@RequestBody MemberPwdUpdateRequest memberPwdUpdateRequest) {
        return adminMemberService.adminMemberPwdUpdate(memberPwdUpdateRequest);
    }


}


