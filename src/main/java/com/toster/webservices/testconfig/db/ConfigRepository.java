package com.toster.webservices.testconfig.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends CrudRepository<ConfigRow, Long>{


}
