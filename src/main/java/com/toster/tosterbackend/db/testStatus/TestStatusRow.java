package com.toster.tosterbackend.db.testStatus;

import com.toster.tosterbackend.db.testCase.TestCaseRow;
import com.toster.tosterbackend.testStatus.model.TestStatus;

import javax.persistence.*;

@Entity
public class TestStatusRow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private TestCaseRow testCase;

    private String testStatus;

    private String stackTrace;

    private String runDate;


    private TestStatusRow() {
    }

    public TestStatusRow(TestCaseRow testCase, String testStatus, String stackTrace, String runDate ) {
        this.testCase = testCase;
        this.testStatus = testStatus;
        this.stackTrace = stackTrace;
        this.runDate = runDate;

    }

    public TestStatus toTestStatus() {
        return new TestStatus(
                this.getId(),
                this.getTestCase().getTestName(),
                this.getTestStatus(),
                this.getStackTrace(),
                this.getRunDate());

    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TestCaseRow getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCaseRow testCase) {
        this.testCase = testCase;
    }

    public String getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(String testStatus) {
        this.testStatus = testStatus;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public String getRunDate() {
        return runDate;
    }

    public void setRunDate(String runDate) {
        this.runDate = runDate;
    }
}
