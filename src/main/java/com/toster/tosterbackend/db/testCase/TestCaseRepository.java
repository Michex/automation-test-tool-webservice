package com.toster.tosterbackend.db.testCase;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCaseRepository extends CrudRepository<TestCaseRow, Long> {



}
