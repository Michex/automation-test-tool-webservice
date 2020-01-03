package com.toster.tosterbackend.db;

import javax.persistence.*;

@Entity
public class TestCaseRow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String appName;

    private String testName;

    public TestCaseRow() {

    }

    public TestCaseRow(String testName, String appName) {
        this.testName = testName;
        this.appName = appName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
}
