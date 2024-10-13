package com.terry.demo.test.testBatch.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.terry.demo.test.testBatch.repository.TestBatchRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class TestBatchRepositoryImpl implements TestBatchRepositoryCustom {

    private final JPAQueryFactory jPAQueryFactory;

}

