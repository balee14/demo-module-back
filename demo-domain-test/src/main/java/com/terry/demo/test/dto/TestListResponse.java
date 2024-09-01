package com.terry.demo.test.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestListResponse {

    private List<TestsDto> testList;
    private Long allCount;

}
