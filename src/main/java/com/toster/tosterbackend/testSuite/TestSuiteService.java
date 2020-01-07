package com.toster.tosterbackend.testSuite;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toster.tosterbackend.config.Config;
import com.toster.tosterbackend.db.TestSuiteRepository;
import com.toster.tosterbackend.db.TestSuiteRow;

import com.toster.tosterbackend.testCase.model.TestCase;
import com.toster.tosterbackend.testSuite.model.*;
import io.vavr.collection.List;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.function.Function;


@Service
public class TestSuiteService {

    private final TestSuiteRepository repository;

    public TestSuiteService(TestSuiteRepository repository) {
        this.repository = repository;
    }


    public TestSuite addTestSuite(NewTestSuite newTestSuite) {

        TestSuiteRow testSuiteRow =  this.repository.save(new TestSuiteRow(newTestSuite.projectName));
        
        return this.getMappedTestSuiteRowFunction().apply(testSuiteRow);

    }

    public TestStatus runTest(NewTestSuite newTestSuite) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(Config.getConfig().getTestSuiteFilePath()), newTestSuite);

        return mapper.readValue(new File(Config.getConfig().getTestStatusFilePath()), TestStatus.class);

    }


    private Function<TestSuiteRow, TestSuite> getMappedTestSuiteRowFunction() {
        return dbObj -> new TestSuite(
                dbObj.getId(),
                dbObj.getProjectInfo(),
                List.ofAll(dbObj.getTestCases()).map(dbTestCase -> new TestCase(dbTestCase.getId(), dbTestCase.getTestName())));
    }
}
