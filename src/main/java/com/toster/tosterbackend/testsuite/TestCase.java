package com.toster.tosterbackend.testsuite;


public class TestCase extends NewTestCase {

    public final long id;

    public TestCase(long id, String appName, String testName) {
        super(appName, testName);
        this.id = id;
    }

}
