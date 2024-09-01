package com.terry.demo.file.dto;

import com.terry.demo.test.dto.TestsDto;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestFileListResponse {

    private List<TestsDto> testList;

}
