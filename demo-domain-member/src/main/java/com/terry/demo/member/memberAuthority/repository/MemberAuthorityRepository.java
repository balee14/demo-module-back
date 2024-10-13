package com.terry.demo.member.memberAuthority.repository;

import com.terry.demo.core.entity.PfMemberRelationAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberAuthorityRepository extends JpaRepository<PfMemberRelationAuthority, String> {


}
