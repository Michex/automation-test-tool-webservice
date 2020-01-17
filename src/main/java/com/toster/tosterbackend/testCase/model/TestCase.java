package com.toster.tosterbackend.testCase.model;


public class TestCase extends NewTestCase {

    public final long id;

    public final long testSuiteId;

    public TestCase(long id, String testName, long testSuiteId) {
        super(testName);
        this.id = id;
        this.testSuiteId = testSuiteId;
    }




}
