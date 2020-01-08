package com.toster.tosterbackend;

import com.toster.tosterbackend.db.TestCaseRepository;
import com.toster.tosterbackend.db.TestSuiteRepository;
import com.toster.tosterbackend.testCase.TestCaseService;
import com.toster.tosterbackend.testCase.model.NewTestCase;
import com.toster.tosterbackend.testCase.model.TestCase;
import com.toster.tosterbackend.testSuite.TestSuiteService;
import com.toster.tosterbackend.testSuite.model.NewTestSuite;
import com.toster.tosterbackend.testSuite.model.TestSuite;
import io.vavr.collection.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCaseServiceTest {

    @Autowired
    public TestCaseRepository testCaseRepository;

    @Autowired
    public TestSuiteRepository testSuiteRepository;





    @After
    public void cleanAfterTest() {
        this.testCaseRepository.deleteAll();
        this.testSuiteRepository.deleteAll();
    }

    @Test
    @Order(1)
    public void getEmptyList() {

        final TestCaseService service = new TestCaseService(testCaseRepository, testSuiteRepository);

        final List<TestCase> testModels = service.getTests();
        Assert.assertTrue(testModels.isEmpty());

    }

    @Test
    @Order(2)
    public void addTestSuiteToDb() {

        final TestSuiteService testSuiteService = new TestSuiteService(testSuiteRepository, testCaseRepository);

        final java.util.List<TestCase> empty = new ArrayList<TestCase>();

        TestSuite testSuite1 = testSuiteService.addTestSuite(new NewTestSuite("Projekt1", empty));
        TestSuite testSuite2 = testSuiteService.addTestSuite(new NewTestSuite("Projekt2", empty));

        final List<TestSuite> testSuites = testSuiteService.getTestSuites();
        Assert.assertNotEquals(testSuite1.id, testSuite2.id);
        Assert.assertEquals(2, testSuites.size());

    }

    @Test
    @Order(3)
    public void addTestCaseToDb() {

        final TestCaseService testCaseService = new TestCaseService(testCaseRepository, testSuiteRepository);

        TestCase testCase1 = testCaseService.addTest(new NewTestCase("Test"));
        TestCase testCase2 = testCaseService.addTest(new NewTestCase("Test2"));


        final List<TestCase> testModels = testCaseService.getTests();

        Assert.assertNotEquals(testCase1.id, testCase2.id);
        Assert.assertEquals(2, testModels.size());


    }

    @Test
    @Order(4)
    public void setTestCaseInTestSuite() {

        final TestSuiteService testSuiteService = new TestSuiteService(testSuiteRepository, testCaseRepository);
        final TestCaseService testCaseService = new TestCaseService(testCaseRepository, testSuiteRepository);

        final java.util.List<TestCase> empty = new ArrayList<TestCase>();

        TestCase testCase1 = testCaseService.addTest(new NewTestCase("Test"));
        TestSuite testSuite1 = testSuiteService.addTestSuite(new NewTestSuite("Projekt1", empty));



        long idTestSuite = testSuite1.id;
        long idTestCase = testCase1.id;

        Optional<TestSuite> testSuiteOptional = testSuiteService.setTestCaseToTestSuite(idTestSuite, idTestCase);

        TestSuite testSuite = testSuiteOptional.orElseThrow(() -> new IllegalArgumentException("Test Suite of id: " + idTestSuite + " does not exist"));

        Assert.assertEquals(1, testSuite.testCases.size());

    }

    @Test
    @Order(5)
    public void setTestCasesInTestSuite() {

        final TestSuiteService testSuiteService = new TestSuiteService(testSuiteRepository, testCaseRepository);
        final TestCaseService testCaseService = new TestCaseService(testCaseRepository, testSuiteRepository);

        final java.util.List<TestCase> empty = new ArrayList<TestCase>();

        TestCase testCase1 = testCaseService.addTest(new NewTestCase("Test"));
        TestCase testCase2 = testCaseService.addTest(new NewTestCase("Test2"));


        TestSuite testSuite1 = testSuiteService.addTestSuite(new NewTestSuite("Projekt1", empty));



        long idTestSuite = testSuite1.id;
        long idTestCase1 = testCase1.id;
        long idTestCase2 = testCase2.id;


         testSuiteService.setTestCaseToTestSuite(idTestSuite, idTestCase1);
         testSuiteService.setTestCaseToTestSuite(idTestSuite, idTestCase2);

         TestSuite testSuite = testSuiteService.getTestSuite(idTestSuite);

         Assert.assertEquals(2, testSuite.testCases.size());


    }


}
