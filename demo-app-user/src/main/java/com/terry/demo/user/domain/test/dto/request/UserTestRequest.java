package com.terry.demo.user.domain.test.dto.request;


import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserTestRequest {

    // @Comment("id")
    @Setter
    private String testId;

    // @Comment("사용자ID")
    private String idEmail;

    // @Comment("설명")
    private String description;

}
