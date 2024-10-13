package com.terry.demo.member.member.service;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.terry.demo.core.entity.PfMember;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static com.terry.demo.core.entity.QPfMember.pfMember;

@Log4j2
@Service
public class MemberPredicate {

    /**
     * 정렬기능
     * @param sort
     * @return
     */
    protected List<OrderSpecifier> getOrderSpecifier(Sort sort) {

        List<OrderSpecifier> orders = new ArrayList<>();

        // Sort
        sort.stream().forEach(
                order -> {
                    Order direaction = order.isAscending() ? Order.ASC : Order.DESC;
                    switch (order.getProperty()){
                        case "partnerName":
                            PathBuilder orderByExpression1 = new PathBuilder(PfMember.class, "partnerName");
                            orders.add(new OrderSpecifier(direaction, orderByExpression1));
                            break;
                        case "leaderName":
                            PathBuilder orderByExpression2 = new PathBuilder(PfMember.class, "leaderName");
                            orders.add(new OrderSpecifier(direaction, orderByExpression2));
                            break;
                    }
                }
        );
        return orders;

    }


//    /**
//     * 사용자유형 검색 LIST
//     * @param memberStateList
//     * @return
//     */
//    protected BooleanExpression memberTypeIn(List<MemberState> memberStateList) {
//        return ObjectUtils.isEmpty(memberStateList) ? null : pfMember.memberState.in((Collection<? extends MemberState>) memberStateList);
//    }


    /**
     * 사용자 id, 이름  검색
     * @param memberSearchType, memberSearchInput
     * @return
     */
    protected BooleanExpression memberNameIdLike(String memberSearchType, String memberSearchInput) {
        if (memberSearchType!= null && memberSearchType.equals("idEmail")) {
            return StringUtils.hasText(memberSearchType) ? pfMember.idEmail.like("%" + memberSearchType + "%") : null;
        }
        if (memberSearchType!= null && memberSearchType.equals("name")) {
            return StringUtils.hasText(memberSearchType) ? pfMember.memberName.like("%" + memberSearchType + "%") : null;
        }
        return null;
    }



    /**
     * 변수로 필터 구분
     * @param code           사용여부 또는 가입구분 코드
     * @param enumConverter  Enum 변환 함수
     * @param <T>            Enum 타입
     * @return
     */
    protected <T extends Enum<T>> BooleanExpression isEnumIn(String code, Function<String, T> enumConverter, EnumPath<T> enumPath) {
        T enumValue = code != null && !code.isEmpty() ? enumConverter.apply(code) : null;
        return enumValue != null ? enumPath.eq(enumValue) : null;
    }

}

