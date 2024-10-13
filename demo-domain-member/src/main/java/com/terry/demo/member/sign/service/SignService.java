package com.terry.demo.member.sign.service;

import com.terry.demo.core.dto.sign.SignMemberDto;
import com.terry.demo.core.util.PfMemberUtil;
import com.terry.demo.member.core.jwt.JwtTokenProvider;
import com.terry.demo.member.membertoken.repository.MemberTokenRepository;
import com.terry.demo.member.sign.request.SignRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class SignService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberTokenRepository memberTokenRepository;

    /**
     * 로그인
     * @param signRequest
     * @return
     */
    public SignMemberDto getMemberSignIn(SignRequest signRequest) {

        //
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(signRequest.getIdEmail(), signRequest.getMemberPwd());

        // authenticate 메소드가 실행이 될 때 loadUserByUsername 메소드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        //
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.debug("Security Context에 '{}' 인증 정보를 저장했습니다", signRequest.getIdEmail());

        // 사용자 공통 설정
        return SignMemberDto.builder()
                .memberId(PfMemberUtil.getMemberId())
                .idEmail(PfMemberUtil.getIdEmail())
                .memberName(PfMemberUtil.getMemberName())
                .memberAuthority(PfMemberUtil.getMemberAuthority())
                .build();

    }

    /**
     * 로그아웃
     * @param request, response
     * @return
     */
    public void getMemberSignOut(HttpServletRequest request, HttpServletResponse response) {

        String accessToken = jwtTokenProvider.getHeaderAccessToken(request);
        memberTokenRepository.memberTokenErrorAccessToken(accessToken);

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

    }



}
