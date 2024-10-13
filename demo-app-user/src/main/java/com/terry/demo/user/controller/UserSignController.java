package com.terry.demo.user.controller;

import com.terry.demo.core.dto.common.CommonCustomResponse;
import com.terry.demo.core.dto.common.CommonResponseEntity;
import com.terry.demo.core.dto.common.CommonResponseEntityType;
import com.terry.demo.user.domain.sign.UserSignService;
import com.terry.demo.user.domain.sign.dto.request.UserSignRequest;
import com.terry.demo.user.domain.sign.dto.response.UserSignResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user/v1")
@RequiredArgsConstructor
public class UserSignController {

    /**
     * 로그인, 로그아웃 및 토큰관리
     */

    private final UserSignService userSignService;

    /**
     * user 로그인
     */
    @PostMapping("/sign/in")
    public ResponseEntity<?> userSignIn(@RequestBody UserSignRequest userSignRequest) {

        UserSignResponse userSignResponse = userSignService.getUserSignIn(userSignRequest);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, userSignResponse)
                , userSignResponse.getHeaders(), CommonResponseEntityType.OK.getHttpStatus());

    }

    /**
     * user accessToken 재발급
     */
    @PostMapping("/sign/accesstoken")
    public ResponseEntity<?> userAccesstoken(HttpServletRequest request, HttpServletResponse response) {

        UserSignResponse userSignResponse = userSignService.getUserAccesstoken(request, response);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, userSignResponse)
                , userSignResponse.getHeaders(), CommonResponseEntityType.OK.getHttpStatus());

    }

    /**
     * user 로그아웃
     * todo : get이 아닌 post방식으로 처리 할 것(특별한 경우를 제외하곤 사용하지 않는다.)
     */
    @GetMapping("/sign/logout")
    public ResponseEntity<?> userSignLogout(HttpServletRequest request, HttpServletResponse response){

        CommonCustomResponse commonCustomResponse = userSignService.getUserSignLogout(request, response);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, commonCustomResponse)
                , CommonResponseEntityType.OK.getHttpStatus());

    }

}
