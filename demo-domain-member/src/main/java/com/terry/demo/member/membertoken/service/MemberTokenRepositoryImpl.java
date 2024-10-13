package com.terry.demo.member.membertoken.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.terry.demo.member.membertoken.repository.MemberTokenRepositoryCustom;
import com.terry.demo.member.membertoken.request.MemberTokenReAccessTokenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.terry.demo.core.entity.QPfMemberToken.pfMemberToken;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberTokenRepositoryImpl implements MemberTokenRepositoryCustom {

    private final JPAQueryFactory jPAQueryFactory;

    /**
     * 토큰 재발급
     */
    @Override
    public void memberReAccessTokenUpdate(MemberTokenReAccessTokenRequest memberTokenReAccessTokenRequest) {
        JPAUpdateClause clause = jPAQueryFactory
                .update(pfMemberToken)
                .set(pfMemberToken.accessToken, memberTokenReAccessTokenRequest.getReAccessToken())
                .set(pfMemberToken.accessTokenDt, memberTokenReAccessTokenRequest.getAccessTokenDt())
                .set(pfMemberToken.modDt, LocalDateTime.now())
                .set(pfMemberToken.modId, memberTokenReAccessTokenRequest.getIdEmail())
                .where(pfMemberToken.refreshToken.eq(memberTokenReAccessTokenRequest.getRefreshToken())
                        , pfMemberToken.idEmail.eq(memberTokenReAccessTokenRequest.getIdEmail())
                        , pfMemberToken.isUse.eq(true)
                );
        clause.execute();
    }

    /**
     * idEmail 기준 전체 토큰 미사용 처리
     */
    @Override
    public void memberTokenErrorIdEmail(String idEmail) {
        jPAQueryFactory
                .update(pfMemberToken)
                .set(pfMemberToken.modDt, LocalDateTime.now())
                .set(pfMemberToken.isUse, false)
                .set(pfMemberToken.modId, idEmail)
                .where(
                        pfMemberToken.idEmail.eq(idEmail),
                        pfMemberToken.isUse.eq(true)
                )
                .execute();
    }

    /**
     * refreshToken 미사용처리
     */
    @Override
    public void memberTokenErrorRefreshToken(String refreshToken) {
        jPAQueryFactory
                .update(pfMemberToken)
                .set(pfMemberToken.modDt, LocalDateTime.now())
                .set(pfMemberToken.isUse, false)
                .set(pfMemberToken.modId, refreshToken)
                .where(
                        pfMemberToken.refreshToken.eq(refreshToken),
                        pfMemberToken.isUse.eq(true)
                )
                .execute();
    }

    /**
     * accessToken 미사용 처리
     */
    @Override
    public void memberTokenErrorAccessToken(String accessToken) {
        jPAQueryFactory
                .update(pfMemberToken)
                .set(pfMemberToken.modDt, LocalDateTime.now())
                .set(pfMemberToken.isUse, false)
                .set(pfMemberToken.modId, accessToken)
                .where(
                        pfMemberToken.accessToken.eq(accessToken),
                        pfMemberToken.isUse.eq(true)
                )
                .execute();
    }

    /**
     * 토큰 실패한 경우 error 처리
     */
    /*
    @Override
    public void memberTokenErrorUpdate(MemberTokenErrorRequest memberTokenErrorRequest) {
        JPAUpdateClause clause = jPAQueryFactory
                .update(pfMemberToken)
                .set(pfMemberToken.modDt, LocalDateTime.now())
                .set(pfMemberToken.isUse, false);
        if (!ObjectUtils.isEmpty(memberTokenErrorRequest.getIdEmail())) {
            // 상위 개념인 idEmail이 먼저 처리 해야 함.
            clause.set(pfMemberToken.modId, memberTokenErrorRequest.getIdEmail());
            clause.where(pfMemberToken.idEmail.eq(memberTokenErrorRequest.getIdEmail())
                    , pfMemberToken.isUse.eq(true)
            );
        } else if (!ObjectUtils.isEmpty(memberTokenErrorRequest.getRefreshToken())){
            clause.set(pfMemberToken.modId, memberTokenErrorRequest.getRefreshToken());
            clause.where(pfMemberToken.refreshToken.eq(memberTokenErrorRequest.getRefreshToken())
                    , pfMemberToken.isUse.eq(true)
            );
        } else {
            clause.set(pfMemberToken.modId, memberTokenErrorRequest.getAccessToken());
            clause.where(pfMemberToken.accessToken.eq(memberTokenErrorRequest.getAccessToken())
                    , pfMemberToken.isUse.eq(true)
            );
        }
        clause.execute();
    }
    */

}

