package com.terry.demo.test.repository;

import com.terry.demo.core.dto.test.TestQueryDto;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepositoryCustom {

    /**
     *
     * @param testId
     * @return
     */
    TestQueryDto getTestById(String testId);

}
