package com.toster.tosterbackend.testSuite;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toster.tosterbackend.config.Config;
import com.toster.tosterbackend.db.TestCaseRepository;
import com.toster.tosterbackend.db.TestCaseRow;
import com.toster.tosterbackend.db.TestSuiteRepository;
import com.toster.tosterbackend.db.TestSuiteRow;

import com.toster.tosterbackend.testCase.model.TestCase;
import com.toster.tosterbackend.testSuite.model.*;
import io.vavr.collection.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;


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

    @Transactional
    public Optional<TestSuite> setTestCaseToTestSuite(long idTestSuite, long idTestCase) {

        final Optional<TestSuiteRow> testSuite = this.testSuiteRepository.findById(idTestSuite);

        return testSuite.map( ts -> {
                    ts.setTestCases(List.ofAll(ts.getTestCases()).append(this.testCaseRepository.findById(idTestCase).orElseThrow(() -> new IllegalArgumentException("Test Case of id: " + idTestCase + " does not exist"))).asJava());
                    return ts.toTestSuite();
                }
            );

    }
}
