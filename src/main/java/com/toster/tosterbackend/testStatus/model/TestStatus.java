package com.toster.tosterbackend.testStatus.model;

public class TestStatus extends NewTestStatus{

    private final long id;

    public TestStatus(long id, String testCaseName, String status, String stackTrace, String runDate) {
        super(testCaseName, status, stackTrace, runDate);
        this.id = id;

    }
}
