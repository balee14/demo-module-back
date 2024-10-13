package com.terry.demo.member.member.repository;

import com.terry.demo.core.dto.member.MemberQueryDto;
import com.terry.demo.core.dto.member.MembersQueryDto;
import com.terry.demo.member.member.request.MemberListRequest;
import com.terry.demo.member.member.request.MemberUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepositoryCustom {

    /**
     * 사용자 목록 조회
     * @return
     */
    Page<MembersQueryDto> getMemberList(MemberListRequest memberListRequest, Pageable pageable);


    /**
     * member 조회
     * @param memberId
     * @return
     */
    MemberQueryDto getMemberById(String memberId);

    /**
     *
     * @param memberUpdateRequest
     * @return
     */
    long memberUpdate(MemberUpdateRequest memberUpdateRequest);

}
