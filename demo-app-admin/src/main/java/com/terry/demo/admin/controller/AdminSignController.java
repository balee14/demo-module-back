package com.terry.demo.admin.controller;


import com.terry.demo.admin.domain.sign.AdminSignService;
import com.terry.demo.admin.domain.sign.dto.request.AdminSignRequest;
import com.terry.demo.admin.domain.sign.dto.response.AdminSignResponse;
import com.terry.demo.core.dto.common.CommonCustomResponse;
import com.terry.demo.core.dto.common.CommonResponseEntity;
import com.terry.demo.core.dto.common.CommonResponseEntityType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/admin/v1")
@RequiredArgsConstructor
public class AdminSignController {

    /**
     * 로그인, 로그아웃 및 토큰관리
     */

    private final AdminSignService adminSignService;

    /**
     * admin 로그인
     */
    @PostMapping("/sign/in")
    public ResponseEntity<?> adminSignIn(@RequestBody AdminSignRequest adminSignRequest) {

        AdminSignResponse adminSignResponse = adminSignService.getAdminSignIn(adminSignRequest);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, adminSignResponse)
                , adminSignResponse.getHeaders(), CommonResponseEntityType.OK.getHttpStatus());

    }

    /**
     * admin accessToken 재발급
     */
    @PostMapping("/sign/accessToken")
    public ResponseEntity<?> adminAccessToken(HttpServletRequest request) {

        AdminSignResponse adminSignResponse = adminSignService.getAdminAccessToken(request);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, adminSignResponse)
                , adminSignResponse.getHeaders(), CommonResponseEntityType.OK.getHttpStatus());

    }

    /**
     * admin 로그아웃
     * todo: get이 아닌 post방식으로 처리 할 것(특별한 경우를 제외하곤 사용하지 않는다.)
     */
    @GetMapping("/sign/logout")
    public ResponseEntity<?> adminSignLogout(HttpServletRequest request, HttpServletResponse response){

        CommonCustomResponse commonCustomResponse = adminSignService.getAadminSignLogout(request, response);

        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, commonCustomResponse)
                , CommonResponseEntityType.OK.getHttpStatus());

    }



}
