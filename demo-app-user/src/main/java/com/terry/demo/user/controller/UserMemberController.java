package com.terry.demo.user.controller;

import com.terry.demo.core.dto.common.CommonResponseEntity;
import com.terry.demo.core.dto.common.CommonResponseEntityType;
import com.terry.demo.user.domain.member.UserMemberService;
import com.terry.demo.user.domain.member.dto.request.UserMemberListRequest;
import com.terry.demo.user.domain.member.dto.request.UserMemberRequest;
import com.terry.demo.user.domain.member.dto.request.UserMemberUpdateRequest;
import com.terry.demo.user.domain.member.dto.response.UserMemberCntResponse;
import com.terry.demo.user.domain.member.dto.response.UserMemberDtoResponse;
import com.terry.demo.user.domain.member.dto.response.UserMemberListResponse;
import com.terry.demo.user.domain.member.dto.response.UserMemberQueryDtoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user/v1")
public class UserMemberController {

    /**
     * 사용자 관리
     */

    private final UserMemberService userMemberService;

    /**
     * user 모든 member 목록 조회
     */
    @GetMapping("/member/members")
    public ResponseEntity<?> getUserMemberList(@ModelAttribute UserMemberListRequest userMemberListRequest, Pageable pageable) {

        UserMemberListResponse userMemberListResponse = userMemberService.getUserMemberList(userMemberListRequest, pageable);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, userMemberListResponse)
                , CommonResponseEntityType.OK.getHttpStatus());

    }

    /**
     * user member 조회
     */
    @GetMapping("/member/memberId/{memberId}")
    public ResponseEntity<?> getUserMemberById(@PathVariable String memberId) {

        UserMemberQueryDtoResponse userMemberDtoResponse = userMemberService.getUserMemberById(memberId);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, userMemberDtoResponse)
                , CommonResponseEntityType.OK.getHttpStatus());

    }

    /**
     * user member 등록
     */
    @PostMapping("/member")
    public ResponseEntity<?> userMemberSave(@RequestBody UserMemberRequest userMemberRequest) {

        UserMemberDtoResponse userMemberDtoResponse = userMemberService.userMemberSave(userMemberRequest);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, userMemberDtoResponse)
                , CommonResponseEntityType.OK.getHttpStatus());

    }

    /**
     * user member 업데이트
     */
    @PutMapping("/member")
    public ResponseEntity<?> userMemberUpdate(@RequestBody UserMemberUpdateRequest userMemberUpdateRequest) {

        UserMemberCntResponse userMemberCntResponse = userMemberService.userMemberUpdate(userMemberUpdateRequest);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, userMemberCntResponse)
                , CommonResponseEntityType.OK.getHttpStatus());

    }

}
