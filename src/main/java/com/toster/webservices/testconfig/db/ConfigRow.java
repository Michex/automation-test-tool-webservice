package com.toster.webservices.testconfig.db;

import com.toster.webservices.testsuite.db.TestSuiteRow;

import javax.persistence.*;

@Entity
public class ConfigRow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String path;

    @ManyToOne
    private TestSuiteRow testSuiteRow;


    protected ConfigRow() {
    }

    public ConfigRow(String path, TestSuiteRow testSuiteRow) {
        this.path = path;
        this.testSuiteRow = testSuiteRow;
    }

    public long getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public TestSuiteRow getTestSuiteRow() {
        return testSuiteRow;
    }

    public void setTestSuiteRow(TestSuiteRow testSuiteRow) {
        this.testSuiteRow = testSuiteRow;
    }
}
