package com.toster.tosterbackend.testCase;

import com.toster.tosterbackend.db.TestCaseRepository;
import com.toster.tosterbackend.db.TestCaseRow;
import com.toster.tosterbackend.db.TestSuiteRepository;
import com.toster.tosterbackend.db.TestSuiteRow;
import com.toster.tosterbackend.testCase.exceptions.NoTestCaseException;
import com.toster.tosterbackend.testCase.model.NewTestCase;
import com.toster.tosterbackend.testCase.model.TestCase;
import com.toster.tosterbackend.testSuite.exceptions.NoTestSuiteException;
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


    @Transactional
    public TestCase addTest(final long testSuiteId, final NewTestCase newTestCase) {

        final Optional<TestSuiteRow> testSuiteRow = this.testSuiteRepository.findById(testSuiteId);
        return testSuiteRow.map( ts -> {
                    final TestCaseRow testCaseRow = new TestCaseRow(
                            newTestCase.testName,
                            ts);

                  return this.testCaseRepository.save(testCaseRow).toTestCase();
                }
        ).orElseThrow(() -> new NoTestSuiteException(testSuiteId));
    }

    public List<TestCase> getTests() {
        return List.ofAll(this.testCaseRepository.findAll())
                .map(TestCaseRow::toTestCase);
    }


    public TestCase getTest(long id) {

        return this.testCaseRepository
                .findById(id)
                .map(tc ->
                        new TestCase(
                                tc.getId(),
                                tc.getTestName(),
                                tc.getTestSuiteRow().getId())).orElseThrow(() -> new NoTestCaseException(id));
    }
}