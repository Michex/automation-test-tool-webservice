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



    @Test
    @Order(1)
    public void addTestSuiteToDb() {

        final TestSuiteService testSuiteService = new TestSuiteService(testSuiteRepository, testCaseRepository);


        final TestSuite testSuite1 = testSuiteService.addTestSuite(new NewTestSuite("Projekt1"));
        final TestSuite testSuite2 = testSuiteService.addTestSuite(new NewTestSuite("Projekt2"));

        final List<TestSuite> testSuites = testSuiteService.getTestSuites();
        Assert.assertNotEquals(testSuite1.id, testSuite2.id);
        Assert.assertEquals(2, testSuites.size());

        final TestCaseService testCaseService = new TestCaseService(testCaseRepository, testSuiteRepository);

        TestCase testCase1 = testCaseService.addTest(testSuite1.id, new NewTestCase("Test"));
        TestCase testCase2 = testCaseService.addTest(testSuite1.id, new NewTestCase("Test2"));


        final List<TestCase> testModels = testCaseService.getTests();

        Assert.assertNotEquals(testCase1.id, testCase2.id);
        Assert.assertEquals(2, testModels.size());



    }






}
