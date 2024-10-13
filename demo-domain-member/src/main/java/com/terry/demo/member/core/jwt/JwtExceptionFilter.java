package com.terry.demo.member.core.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.terry.demo.core.dto.common.CommonCustomResponse;
import com.terry.demo.core.dto.common.CommonCustomType;
import com.terry.demo.core.dto.common.CommonResponseEntity;
import com.terry.demo.core.dto.common.CommonResponseEntityType;
import com.terry.demo.member.membertoken.repository.MemberTokenRepository;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Log4j2
@Component
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberTokenRepository memberTokenRepository;

    /*
    인증 오류가 아닌, JWT 관련 오류는 이 필터에서 따로 잡아낸다.
    이를 통해 JWT 만료 에러와 인증 에러를 따로 잡아낼 수 있다.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            chain.doFilter(request, response); // JwtAuthenticationFilter로 이동
        } catch (JwtException ex) {
            // JwtAuthenticationFilter에서 예외 발생하면 바로 setErrorResponse 호출
            setErrorResponse(request, response, ex);
        }
    }

    public void setErrorResponse(HttpServletRequest request, HttpServletResponse response, Throwable ex) throws IOException {

        ResponseEntity<?> responseEntity;

        String accessToken = jwtTokenProvider.getHeaderAccessToken(request);
        String refreshToken = jwtTokenProvider.getHeaderRefreshToken(request);
        String idEmail = jwtTokenProvider.getIdEmailRefreshToken(refreshToken);

        if(ex.toString().contains("JwtException")) {

            memberTokenRepository.memberTokenErrorAccessToken(accessToken);

            CommonCustomResponse commonCustomResponse = CommonCustomResponse.builder()
                    .customCode(CommonCustomType.getCustomCodeFindAny(ex.getMessage()))
                    .customMessage(CommonCustomType.getCustomMessageFindAny(ex.getMessage()))
                    .build();

            responseEntity = new ResponseEntity<>(
                new CommonResponseEntity<>(CommonResponseEntityType.UNAUTHORIZED, commonCustomResponse)
                , CommonResponseEntityType.UNAUTHORIZED.getHttpStatus());
        } else {

            responseEntity = new ResponseEntity<>(
                new CommonResponseEntity<>(CommonResponseEntityType.UNAUTHORIZED, ex.toString())
                , CommonResponseEntityType.UNAUTHORIZED.getHttpStatus());

        }

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonLogout = objectMapper.writeValueAsString(responseEntity.getBody());

        // application/json;charset=UTF-8
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(jsonLogout);

    }

}
