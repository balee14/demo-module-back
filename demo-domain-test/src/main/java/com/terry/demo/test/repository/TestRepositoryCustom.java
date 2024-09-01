package com.terry.demo.test.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.terry.demo.core.entity.PfTest;
import com.terry.demo.test.dto.QTestDto;
import com.terry.demo.test.dto.QTestsDto;
import com.terry.demo.test.dto.TestDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.terry.demo.test.dto.TestListRequest;
import com.terry.demo.test.dto.TestUpdateRequest;
import com.terry.demo.test.dto.TestsDto;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import static com.terry.demo.core.entity.QPfMember.pfMember;
import static com.terry.demo.core.entity.QPfTest.pfTest;

@Repository
@RequiredArgsConstructor
public class TestRepositoryCustom {

    private final JPAQueryFactory jPAQueryFactory;

    /**
     * 정렬기능
     * @param sort
     * @return
     */
    private List<OrderSpecifier> getOrderSpecifier(Sort sort) {

        List<OrderSpecifier> orders = new ArrayList<>();

        // Sort
        sort.stream().forEach(
            order -> {
                Order direaction = order.isAscending() ? Order.ASC : Order.DESC;
                switch (order.getProperty()){
                    case "partnerName":
                        PathBuilder orderByExpression1 = new PathBuilder(PfTest.class, "partnerName");
                        orders.add(new OrderSpecifier(direaction, orderByExpression1));
                        break;
                    case "leaderName":
                        PathBuilder orderByExpression2 = new PathBuilder(PfTest.class, "leaderName");
                        orders.add(new OrderSpecifier(direaction, orderByExpression2));
                        break;
                }
            }
        );
        return orders;

    }

    /**
     * 사용자 목록 조회
     * @return
     */
    public Page<TestsDto> getTestList(TestListRequest testListRequest, Pageable pageable) {
        QueryResults<TestsDto> memberList = jPAQueryFactory
            .select(new QTestsDto(
                pfTest.testId,
                pfTest.idEmail
                )
            )
            .from(pfTest)
            .orderBy(getOrderSpecifier(pageable.getSort()).stream().toArray(OrderSpecifier[]::new))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();
        return new PageImpl<>(memberList.getResults(), pageable, memberList.getTotal());

    }

    /**
     *
     * @param testId
     * @return
     */
    public TestDto getTestById(Long testId) {
        return jPAQueryFactory
            .select(new QTestDto
                (   pfTest.testId
                ,   pfTest.idEmail)
            )
            .from(pfTest)
            .where(pfTest.testId.eq(testId))
            .fetchOne();
    }

    /**
     *
     * @param testUpdateRequest
     * @return
     */
    public long testUpdate(TestUpdateRequest testUpdateRequest) {
        return jPAQueryFactory
            .update(pfTest)
            .set(pfTest.idEmail, testUpdateRequest.getIdEmail())
            .set(pfTest.modId, testUpdateRequest.getModId())
            .where(pfTest.testId.eq(testUpdateRequest.getTestId()))
            .execute();
    }



}
