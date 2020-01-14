package com.toster.tosterbackend.testSuite;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toster.tosterbackend.config.Config;
import com.toster.tosterbackend.db.TestCaseRepository;
import com.toster.tosterbackend.db.TestSuiteRepository;
import com.toster.tosterbackend.db.TestSuiteRow;

import com.toster.tosterbackend.testSuite.exceptions.NoTestSuiteException;
import com.toster.tosterbackend.testSuite.model.*;
import io.vavr.collection.List;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;


@Service
public class TestSuiteService {

    private final TestSuiteRepository testSuiteRepository;
    private final TestCaseRepository testCaseRepository;


    public TestSuiteService(TestSuiteRepository testSuiteRepository, TestCaseRepository testCaseRepository) {
        this.testSuiteRepository = testSuiteRepository;
        this.testCaseRepository = testCaseRepository;
    }


    public TestSuite addTestSuite(NewTestSuite newTestSuite) {

        return this.testSuiteRepository.save(new TestSuiteRow(newTestSuite.projectName)).toTestSuite();

    }

    public List<TestSuite> getTestSuites() {
        return List.ofAll(this.testSuiteRepository.findAll())
                .map(TestSuiteRow::toTestSuite);
    }



    public TestStatus runTest(NewTestSuite newTestSuite) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(Config.getConfig().getTestSuiteFilePath()), newTestSuite);

        return mapper.readValue(new File(Config.getConfig().getTestStatusFilePath()), TestStatus.class);

    }

    public TestSuite getTestSuite(long id) {
        return this.testSuiteRepository.findById(id).orElseThrow(() -> new NoTestSuiteException(id)).toTestSuite();
    }


}
