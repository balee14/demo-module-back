package com.terry.demo.core.enums;

import com.terry.demo.core.config.enums.EnumMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PfEnumMapperConfig {

    @Bean
    public EnumMapper enumMapper() {
        EnumMapper enumMapper = new EnumMapper();
        //사용하는 Enum 등록하기
        enumMapper.put("MemberStateEnum", PfMemberStateEnum.class);
        return enumMapper;
    }

}
