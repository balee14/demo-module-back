package com.terry.demo.controller;

import com.terry.demo.core.entity.PfMember;
import com.terry.demo.member.dto.MemberPwdUpdateRequest;
import com.terry.demo.member.dto.MemberUpdateRequest;
import com.terry.demo.member.dto.MemberListRequest;
import com.terry.demo.service.member.UserMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user/v1")
public class UserMemberController {

    /**
     * 사용자 관리
     */

    private final UserMemberService userMemberService;

    /**
     * 모든 member 목록 조회
     *
     * @param
     * @return
     */
    @GetMapping("/member/members")
    public ResponseEntity<?> getUserMemberList(@ModelAttribute MemberListRequest memberListRequest, Pageable pageable) {
        return userMemberService.getUserMemberList(memberListRequest, pageable);
    }

    /**
     * member 조회
     *
     * @param
     * @return
     */
    @GetMapping("/member/memberId/{memberId}")
    public ResponseEntity<?> getUserMemberById(@PathVariable("memberId") Long memberId) {
        return userMemberService.getUserMemberById(memberId);
    }

    /**
     * member 등록
     *
     * @param pfMember
     * @return
     */
    @PostMapping("/member")
    public ResponseEntity<?> userMemberSave(@RequestBody PfMember pfMember) {
        return userMemberService.userMemberSave(pfMember);
    }

    /**
     * member 업데이트
     *
     * @param memberUpdateRequest
     * @return
     */
    @PutMapping("/member")
    public ResponseEntity<?> userMemberUpdate(@RequestBody MemberUpdateRequest memberUpdateRequest) {
        return userMemberService.userMemberUpdate(memberUpdateRequest);
    }

    /**
     * member 업데이트
     *
     * @param memberPwdUpdateRequest
     * @return
     */
    @PutMapping("/member/pwd")
    public ResponseEntity<?> userMemberPwdUpdate(@RequestBody MemberPwdUpdateRequest memberPwdUpdateRequest) {
        return userMemberService.userMemberPwdUpdate(memberPwdUpdateRequest);
    }


}


