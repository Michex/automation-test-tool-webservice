package com.toster.tosterbackend.testsuite;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toster.tosterbackend.config.Config;
import com.toster.tosterbackend.db.AppRow;
import com.toster.tosterbackend.db.AutoTestRepository;
import com.toster.tosterbackend.db.TestCaseRow;
import io.vavr.collection.List;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.function.Function;


@Service
public class TestSuiteService {

    private final AutoTestRepository repository;

    public TestSuiteService(AutoTestRepository repository) {
        this.repository = repository;
    }

    public TestCase addTest(NewTestCase newTestModel) {

       TestCaseRow testCaseRow =  this.repository.save(
                new TestCaseRow(
                        newTestModel.testName,
                        newTestModel.appName));

       return this.getTestModelRowTestCaseFunction().apply(testCaseRow);

    }

    public java.util.List<TestCase> getTests() {
        return List.ofAll(this.repository.findAll())
                .map(getTestModelRowTestCaseFunction()).asJava();

    }

    private Function<TestCaseRow, TestCase> getTestModelRowTestCaseFunction() {
        return dbObj -> new TestCase(
                dbObj.getId(),
                dbObj.getAppName(),
                dbObj.getTestName());
    }

    public TestStatus runTest(TestCase testCase) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(new File(Config.getConfig().getTestSuiteFilePath()), testCase);

        return mapper.readValue(new File(Config.getConfig().getTestStatusFilePath()), TestStatus.class);

    }


}
