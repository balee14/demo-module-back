package com.terry.demo.member.member.repository;

import com.terry.demo.core.entity.PfMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<PfMember, String>, MemberRepositoryCustom {

    Optional<PfMember> findByIdEmail(String email);

}
