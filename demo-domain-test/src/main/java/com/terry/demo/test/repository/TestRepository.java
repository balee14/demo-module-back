package com.terry.demo.test.repository;

import com.terry.demo.core.entity.PfTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<PfTest, String>, TestRepositoryCustom {

}
