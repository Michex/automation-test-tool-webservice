package com.toster.tosterbackend.db.testStatus;

import com.toster.tosterbackend.testRunner.model.TestStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestStatusRepository extends CrudRepository<TestStatusRow, Long>, TestStatusRepositoryCustom {


}
