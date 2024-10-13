package com.terry.demo.test.service;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.terry.demo.core.dto.test.QTestQueryDto;
import com.terry.demo.core.dto.test.TestQueryDto;
import com.terry.demo.test.repository.TestRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.terry.demo.core.entity.QPfTest.pfTest;


@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class TestRepositoryImpl implements TestRepositoryCustom {

    private final JPAQueryFactory jPAQueryFactory;

    /**
     * test 조회
     * @param
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public TestQueryDto getTestById(String testId) {
        return jPAQueryFactory
                .select(new QTestQueryDto(
                        pfTest.testId,
                        pfTest.idEmail
                ))
                .from(pfTest)
                .where(pfTest.testId.eq(testId))
                .fetchOne();
    }

}

