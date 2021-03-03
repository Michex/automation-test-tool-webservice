package com.toster.webservices.testcase.model;


public class TestCase extends NewTestCase {

    public final long id;

    public final long testSuiteId;

    public TestCase(long id, String testName, long testSuiteId) {
        super(testName);
        this.id = id;
        this.testSuiteId = testSuiteId;
    }




}
