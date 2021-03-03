package com.toster.webservices.testrunner;

import com.toster.webservices.testconfig.model.Config;
import com.toster.webservices.teststatus.db.TestStatusRepository;
import com.toster.webservices.teststatus.db.TestStatusRow;
import com.toster.webservices.testsuite.db.TestSuiteRepository;
import com.toster.webservices.teststatus.exceptions.NoTestStatusException;
import com.toster.webservices.teststatus.model.TestStatus;
import com.toster.webservices.testsuite.exceptions.NoTestSuiteException;
import com.toster.webservices.testsuite.model.TestSuite;
import com.toster.webservices.tools.Helper;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;


@Service
public class TestRunnerService {

    private final TestSuiteRepository testSuiteRepository;
    private final TestStatusRepository testStatusRepository;
    private static Config config;

    public TestRunnerService(TestSuiteRepository testSuiteRepository, TestStatusRepository testStatusRepository) {
        this.testSuiteRepository = testSuiteRepository;
        this.testStatusRepository = testStatusRepository;
    }

    @Transactional
    public List<TestStatus> runTestSuite(long id) {

        TestSuite testSuite = this.testSuiteRepository.findById(id).orElseThrow(() -> new NoTestSuiteException(id)).toTestSuite();
        String currentDate = Helper.getCurrentDate();
        return Try.of(() -> {
            final ProcessBuilder pBuilder = new ProcessBuilder();
            pBuilder.command(config.path, currentDate);
            testSuite.testCases.forEach(tc -> pBuilder.command().add(tc.testName.replaceAll(" ", "")));
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
