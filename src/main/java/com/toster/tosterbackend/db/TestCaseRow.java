package com.toster.tosterbackend.db;

import javax.persistence.*;

@Entity
public class TestCaseRow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String testName;

    @ManyToOne
    private TestSuiteRow testSuiteRow;


    public TestCaseRow() {
    }

    public TestCaseRow(String testName) {
        this.testName = testName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public TestSuiteRow getTestSuiteRow() {
        return testSuiteRow;
    }

    public void setTestSuiteRow(TestSuiteRow testSuiteRow) {
        this.testSuiteRow = testSuiteRow;
    }
}
