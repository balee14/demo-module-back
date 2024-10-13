package com.terry.demo.core.util;

import com.terry.demo.core.dto.common.CommonCustomType;
import com.terry.demo.core.dto.common.CommonRuntimeException;
import com.terry.demo.core.entity.PfMemberPrincipal;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class PfMemberUtil {

    /**
     * 회원 아이디
     * @return
     */
    public static String getMemberId() {

        String memberId = null;

        // authentication객체가 저장되는 시점은 JwtFilter의 doFilter 메소드에서
        // Request가 들어올 때 SecurityContext에 Authentication 객체를 저장해서 사용
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            //log.debug("Security Context에 인증 정보가 없습니다.");
            throw new CommonRuntimeException(CommonCustomType.CLIENT_ERROR_401_31.name());
        }

        PfMemberPrincipal pfMemberPrincipal = (PfMemberPrincipal) authentication.getPrincipal();
        return memberId = pfMemberPrincipal.getPfMember().getMemberId();

    }

    /**
     * 회원 아이디 이메일
     * 로그인이 아닌 경우 'anonymousUser'로 설정됨
     * @return
     */
    public static String getIdEmail() {

        // authentication객체가 저장되는 시점은 JwtFilter의 doFilter 메소드에서
        // Request가 들어올 때 SecurityContext에 Authentication 객체를 저장해서 사용
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            //log.debug("Security Context에 인증 정보가 없습니다.");
            throw new CommonRuntimeException(CommonCustomType.CLIENT_ERROR_401_32.name());
        }

        String membername = null;
        if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            membername = userDetails.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            membername = (String) authentication.getPrincipal();
        }

        return membername;
    }

    /**
     * 회원명
     * @return
     */
    public static String getMemberName() {

        String memberName = "";

        // authentication객체가 저장되는 시점은 JwtFilter의 doFilter 메소드에서
        // Request가 들어올 때 SecurityContext에 Authentication 객체를 저장해서 사용
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            //log.debug("Security Context에 인증 정보가 없습니다.");
            throw new CommonRuntimeException(CommonCustomType.CLIENT_ERROR_401_33.name());
        }

        PfMemberPrincipal pfMemberPrincipal = (PfMemberPrincipal) authentication.getPrincipal();
        memberName = pfMemberPrincipal.getPfMember().getMemberName();

        return ObjectUtils.isEmpty(memberName)? "" : memberName ;

    }

    /**
     * 회원 권한
     * @return
     */
    public static List<String> getMemberAuthority() {

        // authentication객체가 저장되는 시점은 JwtFilter의 doFilter 메소드에서
        // Request가 들어올 때 SecurityContext에 Authentication 객체를 저장해서 사용
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PfMemberPrincipal pfMemberPrincipal = (PfMemberPrincipal) authentication.getPrincipal();
        return pfMemberPrincipal.getPfMember().getPfMemberRelationAuthorityList().stream()
                .map(memberAuthority -> memberAuthority.getPfAuthority().getAuthorityName())
            .collect(Collectors.toList());

    }



}
