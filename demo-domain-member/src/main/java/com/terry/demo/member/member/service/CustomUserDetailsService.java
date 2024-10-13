package com.terry.demo.member.member.service;

import com.terry.demo.core.dto.common.CommonCustomType;
import com.terry.demo.core.dto.common.CommonRuntimeException;
import com.terry.demo.core.entity.PfMember;
import com.terry.demo.core.entity.PfMemberPrincipal;
import com.terry.demo.core.entity.PfMemberToken;
import com.terry.demo.member.member.repository.MemberRepository;
import com.terry.demo.member.membertoken.repository.MemberTokenRepository;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.Optional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final MemberTokenRepository memberTokenRepository;

    /**
     * 비밀번호가 틀렸다고 token 미사용 처리를 하지 않기 때문에 CommonRuntimeException으로 처리.
     * @param idEmail
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String idEmail) throws UsernameNotFoundException {

        PfMember pfMember = Optional.of(memberRepository.findByIdEmail(idEmail)
                .orElseThrow(() -> new CommonRuntimeException(CommonCustomType.CLIENT_ERROR_401_30.name()) )).get();

        // 사용 상태가 아니면 오류
        if (!String.valueOf(pfMember.getMemberState()).equalsIgnoreCase("Y")) {
            throw new CommonRuntimeException(CommonCustomType.CLIENT_ERROR_401_30.name());
        }
        return new PfMemberPrincipal(pfMember);

    }

    /**
     * 유저 확인 후 유저정보를 SecurityContextHolder에 저장한다.
     * SecurityContextHolder에 저장해야 PfMemberUtil을 통해 유저정보를 추출할 수 있다.
     * @param idEmail
     */
    public void userDetailSetSecurityContextHolder(String idEmail, String accessToken, boolean isUse) {

        // AccessToken이 일치 하는지 확인
        PfMemberToken pkMemberAccessToken = memberTokenRepository.findByIdEmailAndAccessTokenAndIsUseAndAccessTokenDtAfter(idEmail, accessToken, isUse, LocalDateTime.now());
        if(ObjectUtils.isEmpty(pkMemberAccessToken)) {
            // accessToken은 true전달, refreshToken은 false 전달
            if (isUse) {
                log.debug("jwt accessToken db null");
                throw new JwtException(CommonCustomType.CLIENT_ERROR_401_12.name());
            } else {
                log.debug("jwt refreshToken db null");
                throw new JwtException(CommonCustomType.CLIENT_ERROR_401_REFRESH_TOKEN_22.name());
            }

        }

        // 유저 정보 확인
        UserDetails userDetails = loadUserByUsername(idEmail);
        if (ObjectUtils.isEmpty(userDetails)) {
            throw new CommonRuntimeException(CommonCustomType.CLIENT_ERROR_401_30.name());
        }

        // SecurityContextHolder 세팅
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        // 해당 객체를 SecurityContextHolder에 저장
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        log.debug("Security Context에 '{}' 인증 정보를 저장했습니다", idEmail);

    }



}
