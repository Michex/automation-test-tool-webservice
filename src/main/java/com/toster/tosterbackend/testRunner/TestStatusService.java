package com.toster.tosterbackend.testRunner;

import com.toster.tosterbackend.db.testCase.TestCaseRepository;
import com.toster.tosterbackend.db.testCase.TestCaseRow;
import com.toster.tosterbackend.db.testStatus.TestStatusRepository;
import com.toster.tosterbackend.db.testStatus.TestStatusRow;
import com.toster.tosterbackend.db.testSuite.TestSuiteRepository;
import com.toster.tosterbackend.testCase.exceptions.NoTestCaseException;
import com.toster.tosterbackend.testRunner.model.NewTestStatus;
import com.toster.tosterbackend.testRunner.model.TestStatus;
import com.toster.tosterbackend.testSuite.exceptions.NoTestSuiteException;
import com.toster.tosterbackend.testSuite.model.TestSuite;
import com.toster.tosterbackend.tools.Helper;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class TestStatusService {

    private final TestSuiteRepository testSuiteRepository;
    private final TestStatusRepository testStatusRepository;
    private final TestCaseRepository testCaseRepository;



    public TestStatusService(TestSuiteRepository testSuiteRepository, TestStatusRepository testStatusRepository, TestCaseRepository testCaseRepository) {
        this.testSuiteRepository = testSuiteRepository;
        this.testStatusRepository = testStatusRepository;
        this.testCaseRepository = testCaseRepository;
    }


    public List<TestStatus> runTestSuite(long id) {

        TestSuite testSuite = this.testSuiteRepository.findById(id).orElseThrow(() -> new NoTestSuiteException(id)).toTestSuite();

        final StringBuilder testNames = new StringBuilder();

        for (Object o : testSuite.testCases) {
            testNames.append(o.toString());
            testNames.append("\t");
        }


        String currentDate = Helper.getCurrentDate();

        Try.run( () -> {

            final ProcessBuilder pBuilder = new ProcessBuilder(
                    "/home/msasin/www/tosterApp/automation-test-tool/build/distributions/automation-test-tool-0.1/bin/automation-test-tool",
                    testNames.toString(),
                    currentDate);

            final Process process = pBuilder.start();
            process.waitFor();
            process.getErrorStream();


        }).onFailure(Throwable::printStackTrace);

        return testStatusRepository.findAllByRunDate(currentDate);

    }


    public TestStatus setTestStatusFromTestApp(NewTestStatus newTestStatus) {

        final String testCaseName = newTestStatus.testCaseName;
        TestCaseRow testCase = this.testCaseRepository.findByTestName(testCaseName);

        return this.testStatusRepository.save(new TestStatusRow(testCase, newTestStatus.status, newTestStatus.stackTrace, newTestStatus.runDate)).toTestStatus();

    }
}