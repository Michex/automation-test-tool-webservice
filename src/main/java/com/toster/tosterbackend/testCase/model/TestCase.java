package com.toster.tosterbackend.testCase.model;


public class TestCase extends NewTestCase {

    public final long id;

    public TestCase(long id, String testName, long testSuiteId) {
        super(testName, testSuiteId);
        this.id = id;
    }




}
