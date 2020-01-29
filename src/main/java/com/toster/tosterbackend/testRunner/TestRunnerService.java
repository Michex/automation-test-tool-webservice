package com.toster.tosterbackend.testRunner;

import com.toster.tosterbackend.db.testCase.TestCaseRepository;
import com.toster.tosterbackend.db.testCase.TestCaseRow;
import com.toster.tosterbackend.db.testStatus.TestStatusRepository;
import com.toster.tosterbackend.db.testStatus.TestStatusRow;
import com.toster.tosterbackend.db.testSuite.TestSuiteRepository;
import com.toster.tosterbackend.testCase.exceptions.NoTestCaseException;
import com.toster.tosterbackend.testStatus.exceptions.NoTestStatusException;
import com.toster.tosterbackend.testStatus.model.NewTestStatus;
import com.toster.tosterbackend.testStatus.model.TestStatus;
import com.toster.tosterbackend.testSuite.exceptions.NoTestSuiteException;
import com.toster.tosterbackend.testSuite.model.TestSuite;
import com.toster.tosterbackend.tools.Helper;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;


@Service
public class TestRunnerService {

    private final TestSuiteRepository testSuiteRepository;
    private final TestStatusRepository testStatusRepository;



    public TestRunnerService(TestSuiteRepository testSuiteRepository, TestStatusRepository testStatusRepository) {
        this.testSuiteRepository = testSuiteRepository;
        this.testStatusRepository = testStatusRepository;
    }

    @Transactional
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

            List<TestStatus> listOfTestStatuses = io.vavr.collection.List.ofAll(testStatusRepository.findAllByRunDate(currentDate)).map(TestStatusRow::toTestStatus).asJava();

            if (listOfTestStatuses.isEmpty()) {
                throw new NoTestStatusException(currentDate);
            }

            return listOfTestStatuses;

        }).onFailure(Throwable::printStackTrace).get();



    }

}