package com.toster.webservices.testcase.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCaseRepository extends CrudRepository<TestCaseRow, Long>, TestCaseRepositoryCustom {

}
