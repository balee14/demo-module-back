package com.terry.demo.core.converter;

import com.terry.demo.core.enums.PfMemberStateEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

@Convert
@Slf4j
public class MemberStateConverter implements AttributeConverter<PfMemberStateEnum, String > {

    @Override
    public String convertToDatabaseColumn(PfMemberStateEnum pfMemberStateEnum) {

        if (pfMemberStateEnum == null) return null;

        return pfMemberStateEnum.name();

    }

    @Override
    public PfMemberStateEnum convertToEntityAttribute(String dbData) {

        if(ObjectUtils.isEmpty(dbData)) return null;

        try {
            return PfMemberStateEnum.getMemberStateKey(dbData);
        } catch (IllegalArgumentException illegalArgumentException) {
            log.error("failure to convert cause unexpected code -> [{}]", dbData, illegalArgumentException);
            throw illegalArgumentException;
        }

    }

}
