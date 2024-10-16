package com.terry.demo.user.controller;

import com.terry.demo.core.dto.common.CommonCustomResponse;
import com.terry.demo.core.dto.common.CommonCustomType;
import com.terry.demo.core.dto.common.CommonResponseEntity;
import com.terry.demo.core.dto.common.CommonResponseEntityType;
import com.terry.demo.member.core.security.CustomAuthenticationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Log4j2
@RestControllerAdvice
public class UserExceptionControllerAdvice {


    /**
     * runtimeExceptionHandler
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e) {

        log.error("Error runtimeExceptionHandler -> {}", e.toString());
        if(e.toString().contains("CommonRuntimeException")) {

            CommonCustomResponse commonCustomResponse = CommonCustomResponse.builder()
                    .customCode(CommonCustomType.getCustomCodeFindAny(e.getMessage()))
                    .customMessage(CommonCustomType.getCustomMessageFindAny(e.getMessage()))
                    .build();

            return switch (CommonCustomType.getResponseCodeFindAny(e.getMessage())) {
                case 200 -> new ResponseEntity<>(
                    new CommonResponseEntity<>(CommonResponseEntityType.OK, commonCustomResponse)
                    , CommonResponseEntityType.OK.getHttpStatus());
                case 401 -> new ResponseEntity<>(
                    new CommonResponseEntity<>(CommonResponseEntityType.UNAUTHORIZED, commonCustomResponse)
                    , CommonResponseEntityType.UNAUTHORIZED.getHttpStatus());
                case 403 -> new ResponseEntity<>(
                    new CommonResponseEntity<>(CommonResponseEntityType.FORBIDDEN, commonCustomResponse)
                    , CommonResponseEntityType.FORBIDDEN.getHttpStatus());
                default -> new ResponseEntity<>(
                    new CommonResponseEntity<>(CommonResponseEntityType.INTERNAL_SERVER_ERROR, e.toString())
                    , CommonResponseEntityType.INTERNAL_SERVER_ERROR.getHttpStatus());
            };

        }
        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.INTERNAL_SERVER_ERROR, e.toString())
            , CommonResponseEntityType.INTERNAL_SERVER_ERROR.getHttpStatus());

    }

    /**
     * Optional 처리할때 오류
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> badRequestExceptionHandler(NoSuchElementException e) {

        log.error("Error badRequestExceptionHandler -> {}", e.toString());
        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.BAD_REQUEST, e.toString())
            , CommonResponseEntityType.BAD_REQUEST.getHttpStatus());

    }

    /**
     * InternalAuthenticationServiceException : 유저 정보 틀림(401)
     */
    @ExceptionHandler({InternalAuthenticationServiceException.class})
    public ResponseEntity<?> unauthorizedExceptionHandler(Exception e) {

        //
        if(e.getMessage().contains("CLIENT_ERROR_401")) {

            CommonCustomResponse commonCustomResponse = CommonCustomResponse.builder()
                    .customCode(CommonCustomType.getCustomCodeFindAny(e.getMessage()))
                    .customMessage(CommonCustomType.getCustomMessageFindAny(e.getMessage()))
                    .build();

            return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.FORBIDDEN, commonCustomResponse)
                , CommonResponseEntityType.FORBIDDEN.getHttpStatus());

        }
        log.error("Error unauthorizedExceptionHandler -> {}", e.toString());
        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.UNAUTHORIZED, e.toString())
            , CommonResponseEntityType.UNAUTHORIZED.getHttpStatus());

    }

    /**
     * 아이디, 비밀번호 틀렸을 경우 --> org.springframework.security.authentication.BadCredentialsException: 자격 증명에 실패하였습니다.
     * 사용자 검증이 안되면 --> org.springframework.security.authentication.UsernameNotFoundException
     * 인증 처리 실패 --> org.springframework.security.authentication.InsufficientAuthenticationException: Full authentication is required to access this resource
     */
    @ExceptionHandler({InsufficientAuthenticationException.class, BadCredentialsException.class, CustomAuthenticationException.class, AuthenticationException.class})
    public ResponseEntity<?> forbiddenExceptionHandler(Exception e) {

        // 아이디, 비밀번호 틀렸을 경우
        if(e.getMessage().contains("자격 증명에 실패하였습니다.")) {

            CommonCustomResponse commonCustomResponse = CommonCustomResponse.builder()
                    .customCode(CommonCustomType.getCustomCodeFindAny(e.getMessage()))
                    .customMessage(CommonCustomType.getCustomMessageFindAny(e.getMessage()))
                    .build();

            return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.OK, commonCustomResponse)
                , CommonResponseEntityType.OK.getHttpStatus());

        }
        log.error("Error forbiddenExceptionHandler -> {}", e.toString());
        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.FORBIDDEN, e.toString())
            , CommonResponseEntityType.FORBIDDEN.getHttpStatus());

    }

    /**
     * internalServerErrorExceptionHandler
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> internalServerErrorExceptionHandler(Exception e) {

        log.error("Error internalServerErrorExceptionHandler -> {}", e.toString());
        return new ResponseEntity<>(new CommonResponseEntity<>(CommonResponseEntityType.INTERNAL_SERVER_ERROR, e.toString())
            , CommonResponseEntityType.INTERNAL_SERVER_ERROR.getHttpStatus());

    }

}
