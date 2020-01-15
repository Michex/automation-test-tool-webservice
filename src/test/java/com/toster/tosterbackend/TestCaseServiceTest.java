package com.toster.tosterbackend;

import com.toster.tosterbackend.db.TestCaseRepository;
import com.toster.tosterbackend.db.TestSuiteRepository;
import com.toster.tosterbackend.testCase.TestCaseService;
import com.toster.tosterbackend.testCase.model.NewTestCase;
import com.toster.tosterbackend.testCase.model.TestCase;
import com.toster.tosterbackend.testSuite.TestSuiteService;
import com.toster.tosterbackend.testSuite.model.NewTestSuite;
import com.toster.tosterbackend.testSuite.model.TestSuite;
import io.vavr.collection.List;;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCaseServiceTest {



    @Autowired
    public TestSuiteService testSuiteService;

    @Autowired
    public TestCaseService testCaseService;

    @Autowired
    public TestCaseRepository testCaseRepository;

    @Autowired
    public TestSuiteRepository testSuiteRepository;

    @After
    public void cleanAfterTest() {
        this.testSuiteRepository.deleteAll();
        this.testCaseRepository.deleteAll();
    }

    @Test
    @Order(1)
    public void createTestSuite() {

        final String testSuiteName1 = "Projekt1";
        final TestSuite testSuite1 = testSuiteService.addTestSuite(new NewTestSuite(testSuiteName1));

        assertEquals(testSuiteName1, testSuite1.projectName);

        final String testSuiteName2 = "Projekt2";
        final TestSuite testSuite2 = testSuiteService.addTestSuite(new NewTestSuite(testSuiteName2));

        assertEquals(testSuiteName2, testSuite2.projectName);


    }

    @Test
    @Order(2)
    public void getTestSuites() {

        testSuiteService.addTestSuite(new NewTestSuite("test1"));
        testSuiteService.addTestSuite(new NewTestSuite("test2"));

        final List<TestSuite> testSuites = testSuiteService.getTestSuites();

        assertEquals(2, testSuites.size());

    }

    @Test
    @Order(3)
    public void getTestSuite() {

        final String name = "Test";
        TestSuite newTestSuite =  testSuiteService.addTestSuite(new NewTestSuite(name));
        final TestSuite testSuite = testSuiteService.getTestSuite(newTestSuite.id);

        assertEquals(name, testSuite.projectName);

    }

    @Test
    @Order(4)
    public void createTestCases() {

        final String testSuiteName = "TestSuite";
        TestSuite testSuite = testSuiteService.addTestSuite(new NewTestSuite(testSuiteName));

        final String testCaseName1 = "Test1";
        final TestCase testCase1 = testCaseService.addTest(testSuite.id ,new NewTestCase(testCaseName1));

        assertAll(
                () -> assertEquals(testCaseName1, testCase1.testName),
                () -> assertEquals(testSuite.id, testCase1.testSuiteId));
    }


    @Test
    @Order(5)
    public void getTestCases() {

        TestSuite testSuite = testSuiteService.addTestSuite(new NewTestSuite("test"));

        testCaseService.addTest(testSuite.id, new NewTestCase("test1"));
        testCaseService.addTest(testSuite.id, new NewTestCase("test2"));


        final List<TestCase> testCases = testCaseService.getTests();

        assertEquals(2, testCases.size());

    }

    @Test
    @Order(6)
    public void getTestCase() {

        TestSuite testSuite = testSuiteService.addTestSuite(new NewTestSuite("test"));

        final String testName = "Test1";
        TestCase testCase = testCaseService.addTest(testSuite.id, new NewTestCase(testName));


        assertEquals(testName, testCaseService.getTest(testCase.id).testName);

    }

    @Test
    @Order(7)
    public void getTestSuiteWithTestCases() {
        TestSuite testSuite = testSuiteService.addTestSuite(new NewTestSuite("test"));
        testCaseService.addTest(testSuite.id, new NewTestCase("test1"));
        testCaseService.addTest(testSuite.id, new NewTestCase("test2"));


        assertEquals(2, testSuiteService.getTestSuite(testSuite.id).testCases.size());

    }


}
