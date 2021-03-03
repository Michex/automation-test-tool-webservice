package com.toster.webservices;

import com.toster.webservices.testcase.db.TestCaseRepository;
import com.toster.webservices.teststatus.db.TestStatusRepository;
import com.toster.webservices.teststatus.db.TestStatusRow;
import com.toster.webservices.testsuite.db.TestSuiteRepository;
import com.toster.webservices.testcase.TestCaseService;
import com.toster.webservices.testcase.model.NewTestCase;
import com.toster.webservices.testcase.model.TestCase;
import com.toster.webservices.teststatus.TestStatusService;
import com.toster.webservices.teststatus.model.NewTestStatus;
import com.toster.webservices.teststatus.model.TestStatus;
import com.toster.webservices.testsuite.TestSuiteService;
import com.toster.webservices.testsuite.model.NewTestSuite;
import com.toster.webservices.testsuite.model.TestSuite;
import com.toster.webservices.tools.Helper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestStatusServiceTest {

    @Autowired
    public TestStatusRepository testStatusRepository;

    @Autowired
    public TestCaseRepository testCaseRepository;

    @Autowired
    public TestSuiteRepository testSuiteRepository;


    @Test
    public void setTestStatus() {

        TestCaseService testCaseService = new TestCaseService(testCaseRepository, testSuiteRepository);
        TestSuiteService testSuiteService = new TestSuiteService(testSuiteRepository);
        TestStatusService testStatusService = new TestStatusService(testStatusRepository, testCaseRepository);

        TestSuite testSuite = testSuiteService.addTestSuite(new NewTestSuite("Test suite"));
        TestCase testCase = testCaseService.addTest(testSuite.id, new NewTestCase("test"));

        String localDate = Helper.getCurrentDate();

        TestStatus testStatus1 = testStatusService.setTestStatusFromTestApp(new NewTestStatus(testCase.testName, "status info", "stacktrace info", localDate));
        TestStatus testStatus2 = testStatusService.setTestStatusFromTestApp(new NewTestStatus(testCase.testName, "status info2", "stacktrace info2", localDate));


        List<TestStatus> testStatuses = io.vavr.collection.List.ofAll(testStatusRepository.findAllByRunDate(localDate)).map(TestStatusRow::toTestStatus).asJava();

        assertEquals(localDate, testStatus2.runDate);

        testStatuses.forEach(ts -> assertEquals(localDate, ts.runDate));

        List<TestStatus> testStatusesByTestCase = io.vavr.collection.List.ofAll(testStatusRepository.findAllByTestCase(testCase.id)).map(TestStatusRow::toTestStatus).asJava();

        assertEquals(2, testStatusesByTestCase.size());

    }


}
