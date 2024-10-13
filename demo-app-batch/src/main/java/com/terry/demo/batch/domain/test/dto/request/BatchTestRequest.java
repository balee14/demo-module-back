package com.terry.demo.batch.domain.test.dto.request;

import com.terry.demo.core.enums.PfJobEnum;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BatchTestRequest {

    private PfJobEnum jobName;

    private String jobParameters;

}

