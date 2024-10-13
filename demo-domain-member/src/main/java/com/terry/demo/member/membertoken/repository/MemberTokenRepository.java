package com.terry.demo.member.membertoken.repository;

import com.terry.demo.core.entity.PfMemberToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MemberTokenRepository extends JpaRepository<PfMemberToken, String>, MemberTokenRepositoryCustom {

    PfMemberToken findByIdEmailAndAccessTokenAndIsUseAndAccessTokenDtAfter(String idEmail, String accessToken, Boolean isUse, LocalDateTime accessTokenDtAfter);

    PfMemberToken findByIdEmailAndRefreshToken(String idEmail, String refreshToken);

}
