package com.toster.tosterbackend.db.testStatus;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestStatusRepository extends CrudRepository<TestStatusRow, Long>, TestStatusRepositoryCustom {


}
