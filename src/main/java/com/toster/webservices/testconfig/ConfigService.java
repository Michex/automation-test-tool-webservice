package com.toster.webservices.testconfig;

import com.toster.webservices.testconfig.db.ConfigRepository;
import com.toster.webservices.testconfig.db.ConfigRow;
import com.toster.webservices.testconfig.model.Config;
import com.toster.webservices.testconfig.model.NewConfig;
import com.toster.webservices.testsuite.db.TestSuiteRepository;
import com.toster.webservices.testsuite.db.TestSuiteRow;
import com.toster.webservices.testsuite.exceptions.NoTestSuiteException;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {

    public final ConfigRepository configRepository;
    public final TestSuiteRepository testSuiteRepository;

    public ConfigService(ConfigRepository configRepository, TestSuiteRepository testSuiteRepository) {
        this.configRepository = configRepository;
        this.testSuiteRepository = testSuiteRepository;
    }


    public Config addConfig(Long testSuiteId, NewConfig newConfig) {
        ConfigRow configRow = configRepository.save(new ConfigRow(newConfig.path, testSuiteRepository.findById(testSuiteId).orElseThrow(() -> new NoTestSuiteException(testSuiteId))));

        return new Config(configRow.getId(), configRow.getPath(), configRow.getTestSuiteRow().toTestSuite());

    }
}
