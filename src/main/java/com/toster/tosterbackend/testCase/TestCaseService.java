package com.toster.tosterbackend.testCase;

import com.toster.tosterbackend.db.TestCaseRepository;
import com.toster.tosterbackend.db.TestCaseRow;
import com.toster.tosterbackend.db.TestSuiteRepository;
import com.toster.tosterbackend.db.TestSuiteRow;
import com.toster.tosterbackend.testCase.model.NewTestCase;
import com.toster.tosterbackend.testCase.model.TestCase;
import com.toster.tosterbackend.testSuite.model.TestSuite;
import io.vavr.collection.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
                        newTestCase.testName)).toTestCase();

    }

    public List<TestCase> getTests() {
        return List.ofAll(this.testCaseRepository.findAll())
                .map(TestCaseRow::toTestCase);
    }

    @Transactional
    public Optional<TestCase> setTestCaseToTestSuite(long idTestSuite, long idTestCase) {

        final Optional<TestCaseRow> testCase = this.testCaseRepository.findById(idTestCase);
        final Optional<TestSuiteRow> testSuiteRow = this.testSuiteRepository.findById(idTestCase);


        return testCase.map( tc -> {
            tc.setTestSuiteRow(testSuiteRow.orElseThrow(() -> new IllegalArgumentException("Test Suite of id: " + idTestSuite + " does not exist")));
                    return tc.toTestCase();
                }
        );

    }



}