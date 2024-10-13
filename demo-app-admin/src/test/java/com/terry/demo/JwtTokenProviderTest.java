package com.terry.demo;

import com.terry.demo.core.dto.sign.SignMemberDto;
import com.terry.demo.member.core.jwt.JwtTokenProvider;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2
@SpringBootTest
class JwtTokenProviderTest {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Test
    @DisplayName("토큰 생성")
    void createAccessToken() {

        //given
        List<String> list = new ArrayList<>();
        list.add("ROLE_ADMIN");

        SignMemberDto signMemberDto = SignMemberDto.builder()
                .memberId("member-0HGCHJMFPPE4Z")
                .idEmail("terry10@pomg.co.kr")
                .memberAuthority(list)
                .build();

        //when
        String createAccessToken = jwtTokenProvider.createAccessToken(signMemberDto);

        //then
        assertNotNull(createAccessToken);
        log.info("createAccessToken -> {}", createAccessToken);

    }

    @Test
    @DisplayName("토큰 검증")
    void isValidateAccessToken() {

        //given
        String token = "eyJraWQiOiJtZW1iZXItMEhHQ0hKTUZQUEU0WiIsImFsZyI6IkhTNTEyIn0.eyJzdWIiOiJ0ZXJyeTEwQHBvbWcuY28ua3IiLCJhdXRoIjoiUk9MRV9BRE1JTiIsImV4cCI6MTcyODM1Mzc2MiwiaWF0IjoxNzI4MjY3MzYyLCJpc3MiOiJwb21nIUAxMjMyIn0.1SxK-3JdXN24yxejI95oVjxrVJ9IhsbVF2ES7lq21G2K8OOaCUV9BvLddi3UZJfjI3LJ98qpvCr7kbQ4b39BcA";

        //when
        boolean isValidateAccessToken = jwtTokenProvider.isValidateAccessToken(token);

        //then
        assertTrue(isValidateAccessToken);
        log.info("isValidateAccessToken -> {}", isValidateAccessToken);

    }

    @Test
    @DisplayName("idEmail 검증")
    void getIdEmailAccessToken() {

        //given
        String token = "eyJraWQiOiJtZW1iZXItMEhHQ0hKTUZQUEU0WiIsImFsZyI6IkhTNTEyIn0.eyJzdWIiOiJ0ZXJyeTEwQHBvbWcuY28ua3IiLCJhdXRoIjoiUk9MRV9BRE1JTiIsImV4cCI6MTcyODM1Mzc2MiwiaWF0IjoxNzI4MjY3MzYyLCJpc3MiOiJwb21nIUAxMjMyIn0.1SxK-3JdXN24yxejI95oVjxrVJ9IhsbVF2ES7lq21G2K8OOaCUV9BvLddi3UZJfjI3LJ98qpvCr7kbQ4b39BcA";

        //when
        String getIdEmailAccessToken = jwtTokenProvider.getIdEmailAccessToken(token);

        //then
        assertNotNull(getIdEmailAccessToken);
        log.info("getIdEmailAccessToken -> {}", getIdEmailAccessToken);

    }

    @Test
    @DisplayName("권한 검증")
    void getAuthority() {

        //given
        String token = "eyJraWQiOiJtZW1iZXItMEhHQ0hKTUZQUEU0WiIsImFsZyI6IkhTNTEyIn0.eyJzdWIiOiJ0ZXJyeTEwQHBvbWcuY28ua3IiLCJhdXRoIjoiUk9MRV9BRE1JTiIsImV4cCI6MTcyODM1ODgyOCwiaWF0IjoxNzI4MjcyNDI4LCJpc3MiOiJwb21nIUAxMjMyIn0.chACpc4SyKRp4sjuPBMjLBJGNaiG_rJi-tiJUTn7K2W66Xl8tG9Y6jPZoOTikgn4iyVF4YE7Iycv2ayiSMUexA";

        //when
        List<String> getAuthority = jwtTokenProvider.getAuthority(token);

        //then
        assertNotNull(getAuthority);
        log.info("getAuthority -> {}", getAuthority);

    }

}