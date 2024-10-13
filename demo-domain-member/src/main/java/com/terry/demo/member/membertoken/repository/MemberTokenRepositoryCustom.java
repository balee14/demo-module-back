package com.terry.demo.member.membertoken.repository;

import com.terry.demo.member.membertoken.request.MemberTokenReAccessTokenRequest;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberTokenRepositoryCustom {

    /**
     * 토큰 재발급
     */
    void memberReAccessTokenUpdate(MemberTokenReAccessTokenRequest memberTokenReAccessTokenRequest);

    /**
     * idEmail 기준 전체 토큰 미사용 처리
     */
    void memberTokenErrorIdEmail(String idEmail);

    /**
     * refreshToken 미사용 처리
     */
    void memberTokenErrorRefreshToken(String refreshToken);

    /**
     * accessToken 미사용 처리
     */
    void memberTokenErrorAccessToken(String accessToken);

    /**
     * 토큰 오류 관련 업데이트
     */
//    void memberTokenErrorUpdate(MemberTokenErrorRequest memberTokenErrorRequest);

}
