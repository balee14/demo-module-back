package com.terry.demo.sign.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SigninResponse {

    private Long memberId;
    private String idEmail;
    private String memberName;
    private List<String> memberAuthority;

}
