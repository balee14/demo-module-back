package com.terry.demo.test.testBatch.repository;


import com.terry.demo.core.entity.PfTestBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestBatchRepository extends JpaRepository<PfTestBatch, String>, TestBatchRepositoryCustom {

}
