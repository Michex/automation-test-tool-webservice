package com.toster.tosterbackend.testCase.model;


import com.toster.tosterbackend.testSuite.model.TestSuite;

public class TestCase extends NewTestCase {

    public final long id;

    public final TestSuite testSuite;

    public TestCase(long id, String testName, TestSuite testSuite) {
        super(testName);
        this.id = id;
        this.testSuite = testSuite;
    }




}
