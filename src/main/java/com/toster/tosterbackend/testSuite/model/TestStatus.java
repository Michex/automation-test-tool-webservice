package com.toster.tosterbackend.testSuite.model;


public class TestStatus {

    private final String testName;
    private final String status;
    private final String cause;

    public TestStatus(String testName, String status, String cause) {
        this.testName = testName;
        this.status = status;
        this.cause = cause;
    }

}
