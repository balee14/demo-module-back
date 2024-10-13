package com.terry.demo.core.config.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EnumMapperValue {

    private final String key;
    private final String value;

    public EnumMapperValue(EnumMapperType enumMapperType) {
        key = enumMapperType.getKey();
        value = enumMapperType.getValue();
    }

}
