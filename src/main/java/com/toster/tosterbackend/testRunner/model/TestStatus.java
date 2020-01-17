package com.toster.tosterbackend.testRunner.model;

public class TestStatus extends NewTestStatus{

    private final long id;

    public TestStatus(long id, long testCaseId, String status, String stackTrace, String runDate) {
        super(testCaseId, status, stackTrace, runDate);
        this.id = id;

    }
}
