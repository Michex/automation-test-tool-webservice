package com.toster.tosterbackend.db.testSuite;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestSuiteRepository extends CrudRepository<TestSuiteRow, Long> {



}
