package com.toster.tosterbackend;

import com.toster.tosterbackend.db.testCase.TestCaseRepository;
import com.toster.tosterbackend.db.testStatus.TestStatusRepository;
import com.toster.tosterbackend.db.testSuite.TestSuiteRepository;
import com.toster.tosterbackend.testCase.TestCaseService;
import com.toster.tosterbackend.testCase.model.NewTestCase;
import com.toster.tosterbackend.testCase.model.TestCase;
import com.toster.tosterbackend.testRunner.TestStatusService;
import com.toster.tosterbackend.testRunner.model.NewTestStatus;
import com.toster.tosterbackend.testRunner.model.TestStatus;
import com.toster.tosterbackend.testSuite.TestSuiteService;
import com.toster.tosterbackend.testSuite.model.NewTestSuite;
import com.toster.tosterbackend.testSuite.model.TestSuite;
import com.toster.tosterbackend.tools.Helper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRunnerServiceTest {

    @Autowired
    public TestStatusRepository testStatusRepository;

    @Autowired
    public TestCaseRepository testCaseRepository;

    @Autowired
    public TestSuiteRepository testSuiteRepository;


    @Test
    public void setTestStatus() {

        TestStatusService testStatusService = new TestStatusService(testSuiteRepository, testStatusRepository, testCaseRepository);
        TestCaseService testCaseService = new TestCaseService(testCaseRepository, testSuiteRepository);
        TestSuiteService testSuiteService = new TestSuiteService(testSuiteRepository);

        TestSuite testSuite = testSuiteService.addTestSuite(new NewTestSuite("Test suite"));
        TestCase testCase = testCaseService.addTest(testSuite.id, new NewTestCase("test"));

        String localDate = Helper.getCurrentDate();

        TestStatus testStatus1 = testStatusService.setTestStatusFromTestApp(new NewTestStatus(testCase.testName, "status info", "stacktrace info", localDate));
        TestStatus testStatus2 = testStatusService.setTestStatusFromTestApp(new NewTestStatus(testCase.testName, "status info2", "stacktrace info2", localDate));


        List<TestStatus> testStatuses = testStatusRepository.findAllByRunDate(localDate);

        assertEquals(localDate, testStatus2.runDate);

        testStatuses.forEach(ts -> assertEquals(localDate, ts.runDate));

    }


}
