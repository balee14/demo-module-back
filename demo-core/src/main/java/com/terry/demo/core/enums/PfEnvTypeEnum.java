package com.terry.demo.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * environment
 */
@Getter
@RequiredArgsConstructor
public enum PfEnvTypeEnum {

    LOCAL("local", false),
    DEV("dev", true),
    PROD("prod", true);

    private final String envTypeNm;

    private final boolean isJwtType;

    public static boolean isJwtType(String envTypeNm) {
        return Arrays.stream(PfEnvTypeEnum.values())
                .filter(v -> v.getEnvTypeNm().equalsIgnoreCase(envTypeNm))
                .findAny()
                .orElse(LOCAL).isJwtType();
    }

}
