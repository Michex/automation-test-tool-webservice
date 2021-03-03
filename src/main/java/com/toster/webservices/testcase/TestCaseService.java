package com.toster.webservices.testcase;

import com.toster.webservices.testcase.db.TestCaseRepository;
import com.toster.webservices.testcase.db.TestCaseRow;
import com.toster.webservices.testsuite.db.TestSuiteRepository;
import com.toster.webservices.testsuite.db.TestSuiteRow;
import com.toster.webservices.testcase.exceptions.NoTestCaseException;
import com.toster.webservices.testcase.model.NewTestCase;
import com.toster.webservices.testcase.model.TestCase;
import com.toster.webservices.testsuite.exceptions.NoTestSuiteException;
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
