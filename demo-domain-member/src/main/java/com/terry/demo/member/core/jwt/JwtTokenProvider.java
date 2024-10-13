package com.terry.demo.member.core.jwt;

import com.terry.demo.core.dto.common.CommonCustomType;
import com.terry.demo.core.dto.sign.SignMemberDto;
import com.terry.demo.core.util.PfCommonUtils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Log4j2
@Component
public class JwtTokenProvider implements InitializingBean {

    private static final String AUTHORITIES_KEY = "auth";
    private final String secretKeyStr;
    private final long expiration;
    private final long refreshExpiration;
    private final String issuer;
    private SecretKey secretKey;

    public JwtTokenProvider(@Value("${jwt.secret-key}") String secretKeyStr
        , @Value("${jwt.expiration}") long expiration
        , @Value("${jwt.refresh-expiration}") long refreshExpiration
        , @Value("${jwt.issuer}") String issuer) {
        this.secretKeyStr = secretKeyStr;
        this.expiration = expiration;
        this.refreshExpiration = refreshExpiration;
        this.issuer = issuer;
    }

    /**
     * 빈이 생성되고 주입을 받은 후에 secret값을 Base64 Decode해서 key 변수에 할당하기 위해
     */
    @Override
    public void afterPropertiesSet() {
        byte[] secretKeyBytes = Decoders.BASE64.decode(secretKeyStr);
        this.secretKey = Keys.hmacShaKeyFor(secretKeyBytes);
    }

    /**
     * accessToken 생성
     * @return
     */
    public String createAccessToken(SignMemberDto signMemberDto) {

        String memberAuthorityStr = PfCommonUtils.listStringToString(signMemberDto.getMemberAuthority(), ",");

        return Jwts.builder()
                .header()
                    .keyId(signMemberDto.getMemberId())
                    .and()
                .subject(signMemberDto.getIdEmail())
                .claim(AUTHORITIES_KEY, memberAuthorityStr) // 사용자 권한
                .signWith(secretKey)
                .expiration(Date.from(Instant.now().plus(expiration, ChronoUnit.DAYS)))    // set Expire Time 해당 옵션 안넣으면 expire안함
                .issuedAt(Date.from(Instant.now(Clock.systemDefaultZone())))
                .issuer(issuer)
                .compact();

    }


    /**
     * refreshToken 생성
     * @return
     */
    public String createRefreshToken(SignMemberDto signMemberDto) {

        return Jwts.builder()
                .header()
                    .keyId(signMemberDto.getMemberId())
                    .and()
                .subject(signMemberDto.getIdEmail())
                .claim(AUTHORITIES_KEY, signMemberDto.getMemberAuthority()) // 사용자 권한
                .signWith(secretKey)
                .expiration(Date.from(Instant.now().plus(refreshExpiration, ChronoUnit.DAYS)))    // set Expire Time 해당 옵션 안넣으면 expire안함
                .issuer(issuer)
                .issuedAt(Date.from(Instant.now(Clock.systemDefaultZone())))
                .compact();

    }

    /**
     * token 유효성 검증
     * @param token
     * @return
     * @throws ExpiredJwtException
     */
    public boolean isValidateAccessToken(String token) {

        try {

            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
            return true;

        } catch (SecurityException e) {
            log.warn("jwt 잘못된 시그니처");
            throw new JwtException(CommonCustomType.CLIENT_ERROR_401_13.name());
        } catch (MalformedJwtException e) {
            log.warn("jwt 유효하지 않은 토큰");
            throw new JwtException(CommonCustomType.CLIENT_ERROR_401_14.name());
        } catch (ExpiredJwtException e) {
            log.warn("jwt 토큰 기한 만료");
            throw new JwtException(CommonCustomType.CLIENT_ERROR_401_15.name());
        } catch (UnsupportedJwtException e) {
            log.warn("jwt 지원되지 않는 토큰");
            throw new JwtException(CommonCustomType.CLIENT_ERROR_401_16.name());
        } catch (IllegalArgumentException e) {
            log.warn("jwt token compact of handler are invalid");
            throw new JwtException(CommonCustomType.CLIENT_ERROR_401_17.name());
        } catch (JwtException e) {
            log.warn("jwt error");
        }
        return false;

    }

    /**
     * refreshToken 유효성 검증
     * @param refreshToken
     * @return
     * @throws ExpiredJwtException
     */
    public boolean isValidateRefreshToken(String refreshToken) {

        try {

            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(refreshToken);
            return true;

        } catch (SecurityException e) {
            log.warn("refresh 잘못된 JWT 시그니처");
        } catch (MalformedJwtException e) {
            log.warn("refresh 유효하지 않은 JWT 토큰");
        } catch (ExpiredJwtException e) {
            log.warn("refresh 토큰 기한 만료");
            throw new JwtException(CommonCustomType.CLIENT_ERROR_401_REFRESH_TOKEN_25.name());
        } catch (UnsupportedJwtException e) {
            log.warn("refresh 지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.warn("refresh JWT token compact of handler are invalid.");
        }
        return false;

    }

    /**
     * token으로 idEmail 획득
     * @param token
     * @return
     */
    public String getIdEmailAccessToken(String token) throws RuntimeException{
        try {

            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();

        } catch (Exception e) {
            log.warn(CommonCustomType.CLIENT_ERROR_401_18.name());
            throw new JwtException(CommonCustomType.CLIENT_ERROR_401_18.name());
        }
    }

    /**
     * refreshToken으로 idEmail 획득
     * @param refreshToken
     * @return
     */
    public String getIdEmailRefreshToken(String refreshToken) throws RuntimeException{
        try {

            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(refreshToken)
                    .getPayload()
                    .getSubject();

        } catch (Exception e) {
            log.warn("jwt refreshToken getIdEmail null");
            //throw new JwtException(CommonCustomType.CLIENT_ERROR_401_REFRESH_TOKEN_23.name());
        }
        return null;
    }

    /**
     * token으로 권한 획득
     *
     * @param token
     * @return
     */
    public List<String> getAuthority(String token) {

        return Arrays.stream(Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get(AUTHORITIES_KEY).toString().split(","))
                .toList();

    }

    /**
     * Request Header 에서 토큰 정보를 획득하는 메소드
     * @param request
     * @return
     */
    public String getHeaderAccessToken(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;

    }

    /**
     * Request의 Header에서 RefreshToken 값을 가져옵니다. "Authorization" : "token'
     * @param request
     * @return
     */
    public String getHeaderRefreshToken(HttpServletRequest request) {

        if(request.getHeader("refreshToken") != null )
            return request.getHeader("refreshToken");
        return null;

    }



}
