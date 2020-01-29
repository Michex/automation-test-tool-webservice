package com.toster.tosterbackend.db.testStatus;

import com.toster.tosterbackend.testRunner.model.TestStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestStatusRepositoryCustom {

    @Query("select ts from TestStatusRow ts where ts.runDate = ?1")
    List<TestStatus> findAllByRunDate(final String runDate);


}
