package com.terry.demo.core.enums;

import com.terry.demo.core.config.enums.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum PfStepEnum implements EnumMapperType {

    // Job 정의
    TEST_TASKLET_STEP("testTaskletStep", "테스트 Tasklet 등록"),
    TEST_ITEM_STEP("testItemStep", "테스트 등록");

    private final String stepId;
    private final String stepName;

    public static PfStepEnum getStepKey(String stepKey) {
        return Arrays.stream(PfStepEnum.values())
                .filter(v -> v.name().equals(stepKey))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Step Key에 %s가 존재하지 않습니다.", stepKey)));
    }

    public static PfStepEnum getStepId(String stepKey) {
        return Arrays.stream(PfStepEnum.values())
                .filter(v -> v.getStepId().equals(stepKey))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Step Key에 %s가 존재하지 않습니다.", stepKey)));
    }

    public static PfStepEnum getStepName(String stepName) {
        return Arrays.stream(PfStepEnum.values())
                .filter(v -> v.getStepName().equals(stepName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Step Name에 %s가 존재하지 않습니다.", stepName)));
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return stepName;
    }

}
