package com.terry.demo.admin.domain.sign.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.http.HttpHeaders;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminSignResponse {

    private String memberId;
    private String idEmail;
    private String memberName;
    private List<String> memberAuthority;

    @JsonIgnore
    private HttpHeaders headers;

}
