package com.terry.demo.core.enums;

import com.terry.demo.core.config.enums.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum PfMemberStateEnum implements EnumMapperType {

    //회원 사용여부
    Y( "사용"),
    N( "미사용");

    private final String memberStateName;

    public static PfMemberStateEnum getMemberStateKey(String memberStateKey) {

        return Arrays.stream(PfMemberStateEnum.values())
                .filter(v -> v.name().equals(memberStateKey))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("회원사용여부 키에 %s가 존재 하지 않습니다.", memberStateKey)));

    }

    public static PfMemberStateEnum getMemberStateName(String memberStateName) {

        return Arrays.stream(PfMemberStateEnum.values())
                .filter(v -> v.getMemberStateName().equals(memberStateName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("회원사용여부 명에 %s가 존재 하지 않습니다.", memberStateName)));

    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return memberStateName;
    }

}
