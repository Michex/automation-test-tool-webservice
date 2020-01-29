package com.toster.tosterbackend.db.testStatus;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestStatusRepositoryCustom {

    @Query("select ts from TestStatusRow ts where ts.runDate = ?1")
    List<TestStatusRow> findAllByRunDate(final String runDate);

    @Query("select ts from TestStatusRow ts where ts.testCase.id = ?1")
    List<TestStatusRow> findAllByTestCase(final long id);

    @Query("select ts from TestStatusRow ts where ts.runDate like ?1%")
    List<TestStatusRow> findAllByDate(final String date);

}
