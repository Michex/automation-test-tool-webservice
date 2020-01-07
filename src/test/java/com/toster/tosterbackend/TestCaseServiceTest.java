package com.toster.tosterbackend;

import com.toster.tosterbackend.db.TestCaseRepository;
import com.toster.tosterbackend.testCase.TestCaseService;
import com.toster.tosterbackend.testCase.model.NewTestCase;
import com.toster.tosterbackend.testCase.model.TestCase;
import com.toster.tosterbackend.testSuite.TestSuiteService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCaseServiceTest {

    @Autowired
    public TestCaseRepository repository;

    @After
    public void cleanAfterTest() {
        this.repository.deleteAll();
    }

    @Test
    public void getEmptyList() {

        final TestCaseService service = new TestCaseService(repository);

        final java.util.List<TestCase> testModels = service.getTests();
        Assert.assertTrue(testModels.isEmpty());

    }

    @Test
    public void addTestCaseToDb() {

        final TestCaseService service = new TestCaseService(repository);

        TestCase testCase1 = service.addTest(new NewTestCase("Test"));
        TestCase testCase2 = service.addTest(new NewTestCase("Test2"));


        final java.util.List<TestCase> testModels = service.getTests();

        Assert.assertNotEquals(testCase1.id, testCase2.id);
        Assert.assertEquals(2, testModels.size());


    }


}
