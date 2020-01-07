package com.toster.tosterbackend;

import com.toster.tosterbackend.db.TestCaseRepository;
import com.toster.tosterbackend.db.TestSuiteRepository;
import com.toster.tosterbackend.testCase.TestCaseService;
import com.toster.tosterbackend.testCase.model.NewTestCase;
import com.toster.tosterbackend.testCase.model.TestCase;
import com.toster.tosterbackend.testSuite.TestSuiteService;
import com.toster.tosterbackend.testSuite.model.NewTestSuite;
import com.toster.tosterbackend.testSuite.model.TestSuite;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCaseServiceTest {

    @Autowired
    public TestCaseRepository testCaseRepository;

    @Autowired
    public TestSuiteRepository testSuiteRepository;

    @Test
    public void getEmptyList() {

        final TestCaseService service = new TestCaseService(testCaseRepository, testSuiteRepository);

        final java.util.List<TestCase> testModels = service.getTests();
        Assert.assertTrue(testModels.isEmpty());

    }

    @Test
    public void addTestSuiteToDb() {

        final TestSuiteService testSuiteService = new TestSuiteService(testSuiteRepository);

        TestSuite testSuite1 = testSuiteService.addTestSuite(new NewTestSuite("Projekt1"));
        TestSuite testSuite2 = testSuiteService.addTestSuite(new NewTestSuite("Projekt2"));

        final List<TestSuite> testSuites = testSuiteService.getTestSuites();
        Assert.assertNotEquals(testSuite1.id, testSuite2.id);
        Assert.assertEquals(2, testSuites.size());

    }

    @Test
    public void addTestCaseToDb() {

        final TestCaseService testCaseService = new TestCaseService(testCaseRepository, testSuiteRepository);

        TestCase testCase1 = testCaseService.addTest(new NewTestCase("Test", 1));
        TestCase testCase2 = testCaseService.addTest(new NewTestCase("Test2", 2));


        final List<TestCase> testModels = testCaseService.getTests();

        Assert.assertNotEquals(testCase1.id, testCase2.id);
        Assert.assertEquals(2, testModels.size());


    }


}
