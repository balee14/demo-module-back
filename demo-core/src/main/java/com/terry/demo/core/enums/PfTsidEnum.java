package com.terry.demo.core.enums;

import com.terry.demo.core.config.enums.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum PfTsidEnum implements EnumMapperType {

    ADDRESS("address", "주소"),
    AUTHORITY("authority", "권한"),
    MEMBER("member", "회원"),
    MEMBER_AUTHORITY("memberAuthority", "권한, 회원 매핑 키"),
    MENU("menu", "메뉴"),
    MENU_CATEGORY("menuCategory", "메뉴 카테고리"),
    MENU_OPTION("menuOption", "메뉴 옵션"),
    MENU_OPTION_CATEGORY("menuOptionCategory", "메뉴 옵션 카테고리"),
    MENU_CATEGORY_RELATION_MENU("menuCategoryRelationMenu", "메뉴 카테고리, 메뉴 연결 키"),
    ORDER("order", "주문"),
    STORE("store", "매장"),
    SIGN("sign", "서명"),
    TEST("test", "테스트");

    private final String tsidId;
    private final String tsidName;

    public static PfTsidEnum getTsidKey(String tsidKey) {
        return Arrays.stream(PfTsidEnum.values())
                .filter(v -> v.name().equals(tsidKey))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("tsid key에 %s가 존재하지 않습니다.", tsidKey)));
    }

    public static PfTsidEnum getTsidId(String tsidKey) {
        return Arrays.stream(PfTsidEnum.values())
                .filter(v -> v.getTsidId().equals(tsidKey))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("tsid id에 %s가 존재하지 않습니다.", tsidKey)));
    }

    public static PfTsidEnum getTsidName(String tsidName) {
        return Arrays.stream(PfTsidEnum.values())
                .filter(v -> v.getTsidName().equals(tsidName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("tsid name에 %s가 존재하지 않습니다.", tsidName)));
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return tsidId;
    }

}
