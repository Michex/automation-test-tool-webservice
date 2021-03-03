package com.toster.webservices.teststatus;

import com.toster.webservices.testcase.db.TestCaseRepository;
import com.toster.webservices.testcase.db.TestCaseRow;
import com.toster.webservices.teststatus.db.TestStatusRepository;
import com.toster.webservices.teststatus.db.TestStatusRow;
import com.toster.webservices.testcase.exceptions.NoTestCaseException;
import com.toster.webservices.teststatus.model.NewTestStatus;
import com.toster.webservices.teststatus.model.TestStatus;
import io.vavr.collection.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TestStatusService {

    private final TestStatusRepository testStatusRepository;
    private final TestCaseRepository testCaseRepository;

    public TestStatusService(TestStatusRepository testStatusRepository, TestCaseRepository testCaseRepository) {
        this.testStatusRepository = testStatusRepository;
        this.testCaseRepository = testCaseRepository;
    }

    @Transactional
    public TestStatus setTestStatusFromTestApp(NewTestStatus newTestStatus) {
        final String testCaseName = newTestStatus.testCaseName;
        Optional<TestCaseRow> testCase = this.testCaseRepository.findByTestName(testCaseName);
        return this.testStatusRepository.save(new TestStatusRow(testCase.orElseThrow(() -> new NoTestCaseException(testCaseName)), newTestStatus.status, newTestStatus.stackTrace, newTestStatus.runDate)).toTestStatus();
    }

    public java.util.List<TestStatus> getTestStatusesByTestCase(long id) {
        return List.ofAll(this.testStatusRepository.findAllByTestCase(id)).map(TestStatusRow::toTestStatus).asJava();
    }

    public java.util.List<TestStatus> getTestStatusesByDate(String date) {
        return List.ofAll(this.testStatusRepository.findAllByDate(date)).map(TestStatusRow::toTestStatus).asJava();
    }
}
