package com.terry.demo.member.member.service;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.terry.demo.core.dto.member.MemberQueryDto;
import com.terry.demo.core.dto.member.MembersQueryDto;
import com.terry.demo.core.dto.member.QMemberQueryDto;
import com.terry.demo.core.dto.member.QMembersQueryDto;
import com.terry.demo.member.member.repository.MemberRepositoryCustom;
import com.terry.demo.member.member.request.MemberListRequest;
import com.terry.demo.member.member.request.MemberUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.terry.demo.core.entity.QPfMember.pfMember;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory jPAQueryFactory;
    private final MemberPredicate memberPredicate;

    /**
     * member 목록 조회
     *
     * @param
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public Page<MembersQueryDto> getMemberList(MemberListRequest memberListRequest, Pageable pageable) {

        // list
        List<MembersQueryDto> memberList = jPAQueryFactory
                .select(new QMembersQueryDto(
                        pfMember.memberId,
                        pfMember.idEmail,
                        pfMember.memberName,
                        pfMember.memberNickName,
                        pfMember.memberEmail,
                        pfMember.memberMobilePhone,
                        pfMember.memberState.stringValue()
                ))
                .from(pfMember)
//                .orderBy(memberPredicate.getOrderSpecifier(pageable.getSort()).stream().toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // count
        JPAQuery<Long> memberCount = jPAQueryFactory.select(pfMember.count())
                .from(pfMember);

        return PageableExecutionUtils.getPage(memberList, pageable, memberCount::fetchOne);

    }

    /**
     * member 조회
     * @param
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public MemberQueryDto getMemberById(String memberId) {
        return jPAQueryFactory
                .select(new QMemberQueryDto(
                        pfMember.memberId,
                        pfMember.idEmail,
                        pfMember.memberName,
                        pfMember.memberNickName,
                        pfMember.memberEmail,
                        pfMember.memberMobilePhone,
                        pfMember.memberState.stringValue()
                ))
                .from(pfMember)
                .where(
                        pfMember.memberId.eq(memberId)
                )
                .fetchOne();
    }

    /**
     *
     * @param memberUpdateRequest
     * @return
     */
    @Override
    public long memberUpdate(MemberUpdateRequest memberUpdateRequest) {
        return jPAQueryFactory
                .update(pfMember)
                .set(pfMember.memberName, memberUpdateRequest.getMemberName())
                .set(pfMember.memberMobilePhone, memberUpdateRequest.getMemberMobilePhone())
                .where(
                        pfMember.memberId.eq(memberUpdateRequest.getMemberId())
                )
                .execute();
    }

}

