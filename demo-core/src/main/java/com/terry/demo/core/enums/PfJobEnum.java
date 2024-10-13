package com.terry.demo.core.enums;

import com.terry.demo.core.config.enums.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum PfJobEnum implements EnumMapperType {

    // Job 정의
    TEST_JOB("testJob", "테스트 등록");

    private final String jobId;
    private final String jobName;

    public static PfJobEnum getJobKey(String jobKey) {
        return Arrays.stream(PfJobEnum.values())
                .filter(v -> v.name().equals(jobKey))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Job Key에 %s가 존재하지 않습니다.", jobKey)));
    }

    public static PfJobEnum getJobId(String jobKey) {
        return Arrays.stream(PfJobEnum.values())
                .filter(v -> v.getJobId().equals(jobKey))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Job Key에 %s가 존재하지 않습니다.", jobKey)));
    }

    public static PfJobEnum getJobName(String jobName) {
        return Arrays.stream(PfJobEnum.values())
                .filter(v -> v.getJobName().equals(jobName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Job Name에 %s가 존재하지 않습니다.", jobName)));
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return jobName;
    }

}
