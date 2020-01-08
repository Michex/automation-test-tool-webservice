package com.toster.tosterbackend.testCase;

import com.toster.tosterbackend.db.TestCaseRepository;
import com.toster.tosterbackend.db.TestCaseRow;
import com.toster.tosterbackend.db.TestSuiteRepository;
import com.toster.tosterbackend.testCase.model.NewTestCase;
import com.toster.tosterbackend.testCase.model.TestCase;
import io.vavr.collection.List;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TestCaseService {

    private final TestCaseRepository testCaseRepository;
    private final TestSuiteRepository testSuiteRepository;


    public TestCaseService(TestCaseRepository testCaseRepository, TestSuiteRepository testSuiteRepository) {
        this.testCaseRepository = testCaseRepository;
        this.testSuiteRepository = testSuiteRepository;
    }

    public TestCase addTest(NewTestCase newTestCase) {

        return this.testCaseRepository.save(
                new TestCaseRow(
                        newTestCase.testName,
                        testSuiteRepository.findById(newTestCase.testSuiteId).get()
                )).toTestCase();

    }

    public java.util.List<TestCase> getTests() {
        return List.ofAll(this.testCaseRepository.findAll())
                .map(TestCaseRow::toTestCase).asJava();
    }


}