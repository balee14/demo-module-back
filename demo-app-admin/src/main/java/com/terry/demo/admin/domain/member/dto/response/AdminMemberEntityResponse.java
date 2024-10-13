package com.terry.demo.admin.domain.member.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.terry.demo.core.entity.PfMember;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminMemberEntityResponse {

    @JsonProperty("member")
    private PfMember pfMember;

}
