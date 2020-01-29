package com.toster.tosterbackend.testRunner;

import com.toster.tosterbackend.db.testCase.TestCaseRepository;
import com.toster.tosterbackend.db.testCase.TestCaseRow;
import com.toster.tosterbackend.db.testStatus.TestStatusRepository;
import com.toster.tosterbackend.db.testStatus.TestStatusRow;
import com.toster.tosterbackend.db.testSuite.TestSuiteRepository;
import com.toster.tosterbackend.testCase.exceptions.NoTestCaseException;
import com.toster.tosterbackend.testCase.model.TestCase;
import com.toster.tosterbackend.testRunner.exceptions.NoTestStatusException;
import com.toster.tosterbackend.testRunner.model.NewTestStatus;
import com.toster.tosterbackend.testRunner.model.TestStatus;
import com.toster.tosterbackend.testSuite.exceptions.NoTestSuiteException;
import com.toster.tosterbackend.testSuite.model.TestSuite;
import com.toster.tosterbackend.tools.Helper;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;


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

        String currentDate = Helper.getCurrentDate();

        return Try.of( () -> {

            final ProcessBuilder pBuilder = new ProcessBuilder();
            pBuilder.command("/home/msasin/www/tosterApp/automation-test-tool/build/distributions/automation-test-tool-0.1/bin/automation-test-tool", currentDate);

            testSuite.testCases.forEach(tc -> pBuilder.command().add(tc.testName.replaceAll(" ","")));



            final Process process = pBuilder.start();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);


            return testStatusRepository.findAllByRunDate(currentDate);

        }).onFailure(Throwable::printStackTrace).getOrElseThrow(() -> new NoTestStatusException(currentDate));



    }


    public TestStatus setTestStatusFromTestApp(NewTestStatus newTestStatus) {

        final String testCaseName = newTestStatus.testCaseName;
        Optional<TestCaseRow> testCase = this.testCaseRepository.findByTestName(testCaseName);

        return this.testStatusRepository.save(new TestStatusRow(testCase.orElseThrow(() -> new NoTestCaseException(testCaseName)), newTestStatus.status, newTestStatus.stackTrace, newTestStatus.runDate)).toTestStatus();

    }
}