package com.terry.demo.member.dto;


import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberListResponse {

    private List<MembersDto> memberList;
    private Long allCount;

}

