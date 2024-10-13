package com.terry.demo.admin.domain.test.dto.request;


import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminTestRequest {

    // @Comment("id")
    @Setter
    private String testId;

    // @Comment("사용자ID")
    private String idEmail;

    // @Comment("설명")
    private String description;

}
