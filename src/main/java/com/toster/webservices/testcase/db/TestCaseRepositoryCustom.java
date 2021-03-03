package com.toster.webservices.testcase.db;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestCaseRepositoryCustom {

    @Query("select tc from TestCaseRow tc where tc.testName = ?1")
    Optional<TestCaseRow> findByTestName(final String testName);


}
