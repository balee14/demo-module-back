package com.terry.demo.member.authority.repository;

import com.terry.demo.core.entity.PfAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<PfAuthority, String> {

    Optional<PfAuthority> findByAuthorityName(String authorityName);

}
