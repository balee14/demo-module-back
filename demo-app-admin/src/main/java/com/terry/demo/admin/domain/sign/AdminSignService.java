package com.terry.demo.admin.domain.sign;

import com.terry.demo.admin.domain.sign.dto.request.AdminSignRequest;
import com.terry.demo.admin.domain.sign.dto.response.AdminSignResponse;
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
public class AdminSignService {

    private static final Logger logger = LoggerFactory.getLogger(AdminSignService.class);

    private final SignService signService;
    private final MemberTokenRepository memberTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final long expiration;
    private final long refreshExpiration;

    public AdminSignService(SignService signService,
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
     * admin 로그인
     */
    public AdminSignResponse getAdminSignIn(AdminSignRequest adminSignRequest) {

        SignRequest signRequest = SignRequest.builder()
                .idEmail(adminSignRequest.getIdEmail())
                .memberPwd(adminSignRequest.getMemberPwd())
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
                .idEmail(signRequest.getIdEmail())
                .accessToken(accessToken)
                .accessTokenDt(accessTokenDt)
                .refreshToken(refreshToken)
                .refreshTokenDt(refreshTokenDt)
                .isUse(true)
                .build();
        memberTokenRepository.save(pfMemberToken);

        // HttpHeaders 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add("accessToken", accessToken);
        headers.add("refreshToken", refreshToken);

        return AdminSignResponse.builder()
                .memberId(signMemberDto.getMemberId())
                .idEmail(signMemberDto.getIdEmail())
                .headers(headers)
                .build();

    }

    /**
     * admin 토근 재발급
     */
    public AdminSignResponse getAdminAccessToken(HttpServletRequest request) {

        String idEmail = PfMemberUtil.getIdEmail();
        String refreshToken = jwtTokenProvider.getHeaderRefreshToken(request);

        // refreshToken이 있는지 확인
        PfMemberToken pfMemberToken = memberTokenRepository.findByIdEmailAndRefreshToken(idEmail, refreshToken);
        if(ObjectUtils.isEmpty(pfMemberToken)) {
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

        return AdminSignResponse.builder()
                .headers(headers)
                .build();

    }

    /**
     * admin 로그아웃
     */
    public CommonCustomResponse getAadminSignLogout(HttpServletRequest request, HttpServletResponse response) {

        // 로그아웃
        signService.getMemberSignOut(request, response);

        return CommonCustomResponse.builder()
                .customCode(CommonCustomType.getCustomCodeFindAny(CommonCustomType.SUCCESSFUL_200_1.name()))
                .customMessage(CommonCustomType.getCustomMessageFindAny(CommonCustomType.SUCCESSFUL_200_1.name()))
                .build();

    }

}

