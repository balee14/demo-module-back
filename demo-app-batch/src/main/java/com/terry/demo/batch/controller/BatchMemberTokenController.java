package com.terry.demo.batch.controller;

import com.terry.demo.batch.domain.memberToken.dto.request.BatchMemberTokenRequest;
import com.terry.demo.core.dto.common.CommonResponseEntity;
import com.terry.demo.core.dto.common.CommonResponseEntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/batch/v1")
@RequiredArgsConstructor
public class BatchMemberTokenController {

    /**
     * 회원 토큰 배치 관리
     */

    //private final BatchMemberTokenService batchMemberTokenService;

    /**
     * admin 메뉴 카테고리 등록
     */
    @PostMapping("/memberToekn")
    public ResponseEntity<?> batchMemberToken(@RequestBody BatchMemberTokenRequest batchMemberTokenRequest) {

        //BatchMemberTokenDtoResponse batchMemberTokenDtoResponse = batchMemberTokenService.batchMemberToken(batchMemberTokenRequest);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK)
                , CommonResponseEntityType.OK.getHttpStatus());

    }

}

