package com.terry.demo.test.service;

import com.terry.demo.core.entity.PfTest;
import com.terry.demo.core.util.PfMemberUtil;
import com.terry.demo.test.dto.TestDto;
import com.terry.demo.test.dto.TestListRequest;
import com.terry.demo.test.dto.TestUpdateRequest;
import com.terry.demo.test.dto.TestsDto;
import com.terry.demo.test.repository.TestRepository;
import com.terry.demo.test.repository.TestRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;
    private final TestRepositoryCustom testRepositoryCustom;

    /**
     * test 목록 조회
     *
     * @param
     * @return
     */
    @Transactional(readOnly = true)
    public Page<TestsDto> getTestList(TestListRequest testListRequest, Pageable pageable) {
        return testRepositoryCustom.getTestList(testListRequest, pageable);
    }

    /**
     * test 조회
     * @param
     * @return
     */
    @Transactional(readOnly = true)
    public TestDto getTestById(Long testId) {
        return testRepositoryCustom.getTestById(testId);
    }

    /**
     *
     * @param pfTest
     * @return
     */
    public PfTest testSave(PfTest pfTest) {

        return testRepository.save(pfTest);

    }

    /**
     *
     * @param testUpdateRequest
     * @return
     */
    public Long testUpdate(TestUpdateRequest testUpdateRequest) {

        testUpdateRequest.setModId(PfMemberUtil.getIdEmail());
        return testRepositoryCustom.testUpdate(testUpdateRequest);

    }


}

