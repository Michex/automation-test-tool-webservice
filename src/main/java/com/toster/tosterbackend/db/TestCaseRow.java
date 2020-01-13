package com.toster.tosterbackend.db;

import com.toster.tosterbackend.testCase.model.TestCase;

import javax.persistence.*;

@Entity
public class TestCaseRow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String testName;

    @ManyToOne
    private TestSuiteRow testSuiteRow;


    protected TestCaseRow() {
    }

    public TestCaseRow(String testName, TestSuiteRow testSuiteRow) {
        this.testName = testName;
        this.testSuiteRow = testSuiteRow;
    }

    public TestCase toTestCase() {
        return new TestCase(
                this.getId(),
                this.getTestName(),
                this.getTestSuiteRow().toTestSuite().id);
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
