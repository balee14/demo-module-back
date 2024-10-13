package com.terry.demo.core.dto.sign;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignDto  {

    private String idEmail;

    private String memberPwd;

}
