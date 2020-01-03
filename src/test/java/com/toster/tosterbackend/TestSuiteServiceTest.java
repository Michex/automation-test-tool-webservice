package com.toster.tosterbackend;

import com.toster.tosterbackend.db.AutoTestRepository;
import com.toster.tosterbackend.testsuite.NewTestCase;
import com.toster.tosterbackend.testsuite.TestCase;
import com.toster.tosterbackend.testsuite.TestSuiteService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSuiteServiceTest {

    @Autowired
    public AutoTestRepository repository;

    @After
    public void cleanAfterTest() {
        this.repository.deleteAll();
    }

    @Test
    public void getEmptyList() {

        final TestSuiteService service = new TestSuiteService(repository);

        final java.util.List<TestCase> testModels = service.getTests();
        Assert.assertTrue(testModels.isEmpty());

    }

    @Test
    public void addTestCaseToDb() {

        final TestSuiteService service = new TestSuiteService(repository);

        TestCase testCase1 = service.addTest(new NewTestCase("Google", "Test"));
        TestCase testCase2 = service.addTest(new NewTestCase("Google", "Test"));


        final java.util.List<TestCase> testModels = service.getTests();

        Assert.assertNotEquals(testCase1.id, testCase2.id);
        Assert.assertEquals(2, testModels.size());


    }


}
