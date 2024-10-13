package com.terry.demo.core.dto.sign;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignMemberDto {

    private String memberId;

    private String idEmail;

    private String memberName;

    private List<String> memberAuthority;

}
