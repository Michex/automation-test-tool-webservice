package com.toster.webservices.teststatus.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestStatusRepository extends CrudRepository<TestStatusRow, Long>, TestStatusRepositoryCustom {


}
