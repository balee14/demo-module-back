package com.terry.demo.admin.controller;

import com.terry.demo.admin.domain.member.AdminMemberService;
import com.terry.demo.admin.domain.member.dto.request.AdminMemberListRequest;
import com.terry.demo.admin.domain.member.dto.request.AdminMemberRequest;
import com.terry.demo.admin.domain.member.dto.request.AdminMemberUpdateRequest;
import com.terry.demo.admin.domain.member.dto.response.AdminMemberCntResponse;
import com.terry.demo.admin.domain.member.dto.response.AdminMemberDtoResponse;
import com.terry.demo.admin.domain.member.dto.response.AdminMemberListResponse;
import com.terry.demo.admin.domain.member.dto.response.AdminMemberQueryDtoResponse;
import com.terry.demo.core.config.enums.EnumMapperValue;
import com.terry.demo.core.dto.common.CommonResponseEntity;
import com.terry.demo.core.dto.common.CommonResponseEntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/admin/v1")
public class AdminMemberController {

    /**
     * 사용자 관리
     */

    private final AdminMemberService adminMemberService;

    /**
     * 회원상태 enums 조회
     */
    @GetMapping("/member/memberStateEnums")
    public ResponseEntity<?> getMemberStateEnums() {

        List<EnumMapperValue> menuCategoryList = adminMemberService.getMemberStateEnums();

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, menuCategoryList)
                , CommonResponseEntityType.OK.getHttpStatus());

    }

    /**
     * 모든 member 목록 조회
     */
    @GetMapping("/member/members")
    public ResponseEntity<?> getAdminMemberList(@ModelAttribute AdminMemberListRequest adminMemberListRequest, Pageable pageable) {

        AdminMemberListResponse adminMemberListResponse = adminMemberService.getAdminMemberList(adminMemberListRequest, pageable);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, adminMemberListResponse)
                , CommonResponseEntityType.OK.getHttpStatus());

    }

    /**
     * member 조회
     */
    @GetMapping("/member/memberId/{memberId}")
    public ResponseEntity<?> getAdminMemberById(@PathVariable String memberId) {

        AdminMemberQueryDtoResponse adminMemberQueryDtoResponse = adminMemberService.getAdminMemberById(memberId);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, adminMemberQueryDtoResponse)
                , CommonResponseEntityType.OK.getHttpStatus());

    }

    /**
     * member 등록
     */
    @PostMapping("/member")
    public ResponseEntity<?> adminMemberSave(@RequestBody AdminMemberRequest adminMemberRequest) {

        AdminMemberDtoResponse adminMemberDtoResponse = adminMemberService.adminMemberSave(adminMemberRequest);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, adminMemberDtoResponse)
                , CommonResponseEntityType.OK.getHttpStatus());

    }

    /**
     * member 업데이트
     */
    @PutMapping("/member")
    public ResponseEntity<?> adminMemberUpdate(@RequestBody AdminMemberUpdateRequest adminMemberUpdateRequest) {

        AdminMemberCntResponse adminMemberCntResponse = adminMemberService.adminMemberUpdate(adminMemberUpdateRequest);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, adminMemberCntResponse)
                , CommonResponseEntityType.OK.getHttpStatus());

    }

}


