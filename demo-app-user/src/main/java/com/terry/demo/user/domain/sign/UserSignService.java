package com.terry.demo.user.domain.sign;


import com.terry.demo.core.dto.common.CommonCustomResponse;
import com.terry.demo.core.dto.common.CommonCustomType;
import com.terry.demo.core.dto.sign.SignMemberDto;
import com.terry.demo.core.entity.PfMemberToken;
import com.terry.demo.core.util.PfMemberUtil;
import com.terry.demo.member.core.jwt.JwtTokenProvider;
import com.terry.demo.member.membertoken.repository.MemberTokenRepository;
import com.terry.demo.member.membertoken.request.MemberTokenReAccessTokenRequest;
import com.terry.demo.member.sign.request.SignRequest;
import com.terry.demo.member.sign.service.SignService;
import com.terry.demo.user.domain.sign.dto.request.UserSignRequest;
import com.terry.demo.user.domain.sign.dto.response.UserSignResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

@Log4j2
@Service
@Transactional
public class UserSignService {

    private static final Logger logger = LoggerFactory.getLogger(UserSignService.class);

    private final SignService signService;
    private final MemberTokenRepository memberTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final long expiration;
    private final long refreshExpiration;

    public UserSignService(SignService signService,
                            MemberTokenRepository memberTokenRepository,
                            JwtTokenProvider jwtTokenProvider,
                            @Value("${jwt.expiration}") long expiration,
                            @Value("${jwt.refresh-expiration}") long refreshExpiration) {
        this.signService = signService;
        this.memberTokenRepository = memberTokenRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.expiration = expiration;
        this.refreshExpiration = refreshExpiration;
    }

    /**
     * user 로그인
     */
    public UserSignResponse getUserSignIn(UserSignRequest userSignRequest) {

        SignRequest signRequest = SignRequest.builder()
                .idEmail(userSignRequest.getIdEmail())
                .memberPwd(userSignRequest.getMemberPwd())
                .build();

        // 로그인
        SignMemberDto signMemberDto = signService.getMemberSignIn(signRequest);

        // token 생성
        String accessToken = jwtTokenProvider.createAccessToken(signMemberDto);
        String refreshToken = jwtTokenProvider.createRefreshToken(signMemberDto);

        // 토큰 만료 날짜
        LocalDateTime accessTokenDt = LocalDateTime.now().plusDays(expiration);
        LocalDateTime refreshTokenDt = LocalDateTime.now().plusDays(refreshExpiration);

        // token save
        PfMemberToken pfMemberToken = PfMemberToken.builder()
                .idEmail(signMemberDto.getIdEmail())
                .accessToken(accessToken)
                .accessTokenDt(accessTokenDt)
                .refreshToken(refreshToken)
                .refreshTokenDt(refreshTokenDt)
                .build();
        memberTokenRepository.save(pfMemberToken);

        // HttpHeaders 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add("accessToken", accessToken);
        headers.add("refreshToken", refreshToken);

        return  UserSignResponse.builder()
                .memberId(signMemberDto.getMemberId())
                .idEmail(signMemberDto.getIdEmail())
                .memberName(signMemberDto.getMemberName())
                .memberAuthority(signMemberDto.getMemberAuthority())
                .headers(headers)
                .build();

    }

    /**
     * user 토근 재발급
     */
    public UserSignResponse getUserAccesstoken(HttpServletRequest request, HttpServletResponse response) {

        String idEmail = PfMemberUtil.getIdEmail();
        String refreshToken = jwtTokenProvider.getHeaderRefreshToken(request);

        // refreshToken이 일치 하는지 확인
        PfMemberToken kpMemberRefreshToken = memberTokenRepository.findByIdEmailAndRefreshToken(idEmail, refreshToken);
        if(ObjectUtils.isEmpty(kpMemberRefreshToken)) {
            throw new UsernameNotFoundException(CommonCustomType.CLIENT_ERROR_401_12.name());
        }

        // createAccessToken
        SignMemberDto signMemberDto = SignMemberDto.builder()
                .idEmail(idEmail)
                .memberAuthority(PfMemberUtil.getMemberAuthority())
                .build();
        String reAccessToken = jwtTokenProvider.createAccessToken(signMemberDto);

        // 토큰 만료 일자
        LocalDateTime accessTokenDt = LocalDateTime.now().plusDays(expiration);

        MemberTokenReAccessTokenRequest memberTokenReAccessTokenRequest = MemberTokenReAccessTokenRequest.builder()
                .idEmail(idEmail)
                .refreshToken(refreshToken)
                .reAccessToken(reAccessToken)
                .accessTokenDt(accessTokenDt)
                .build();
        memberTokenRepository.memberReAccessTokenUpdate(memberTokenReAccessTokenRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.add("accessToken", reAccessToken);

        return  UserSignResponse.builder()
                .headers(headers)
                .build();

    }

    /**
     * user 로그아웃
     */
    public CommonCustomResponse getUserSignLogout(HttpServletRequest request, HttpServletResponse response) {

        signService.getMemberSignOut(request, response);

        return CommonCustomResponse.builder()
                .customCode(CommonCustomType.getCustomCodeFindAny(CommonCustomType.SUCCESSFUL_200_1.name()))
                .customMessage(CommonCustomType.getCustomMessageFindAny(CommonCustomType.SUCCESSFUL_200_1.name()))
                .build();

    }



}

