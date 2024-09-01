package com.terry.demo.member.repository;


import com.terry.demo.core.entity.PfMember;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<PfMember, Long> {

    Optional<PfMember> findByIdEmail(String email);

}
