package com.toster.tosterbackend.testCase;

import com.toster.tosterbackend.db.TestCaseRepository;
import com.toster.tosterbackend.db.TestCaseRow;
import com.toster.tosterbackend.testCase.model.NewTestCase;
import com.toster.tosterbackend.testCase.model.TestCase;
import io.vavr.collection.List;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TestCaseService {

    private final TestCaseRepository repository;

    public TestCaseService(TestCaseRepository repository) {
        this.repository = repository;
    }

    public TestCase addTest(NewTestCase newTestModel) {

        TestCaseRow testCaseRow = this.repository.save(
                new TestCaseRow(
                        newTestModel.testName
                ));

        return this.getMappedTestCaseRowFunction().apply(testCaseRow);

    }

    public java.util.List<TestCase> getTests() {
        return List.ofAll(this.repository.findAll())
                .map(getMappedTestCaseRowFunction()).asJava();

    }

    private Function<TestCaseRow, TestCase> getMappedTestCaseRowFunction() {
        return dbObj -> new TestCase(
                dbObj.getId(),
                dbObj.getTestName());
    }
}