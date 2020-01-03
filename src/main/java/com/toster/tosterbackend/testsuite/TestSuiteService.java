package com.toster.tosterbackend.testsuite;

import com.toster.tosterbackend.db.AppRow;
import com.toster.tosterbackend.db.AutoTestRepository;
import com.toster.tosterbackend.db.TestCaseRow;
import io.vavr.collection.List;
import org.springframework.stereotype.Service;

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

    public void runTests() {

    }


}
