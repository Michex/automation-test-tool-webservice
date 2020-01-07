package com.toster.tosterbackend.db;


import javax.persistence.*;
import java.util.List;

@Entity
public class TestSuiteRow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String projectInfo;

    @OneToMany(mappedBy = "testSuiteRow")
    private List<TestCaseRow> testCases;

    public TestSuiteRow(String projectName) {
        this.projectInfo = projectName;
    }

    public TestSuiteRow() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProjectInfo() {
        return projectInfo;
    }

    public void setProjectInfo(String projectInfo) {
        this.projectInfo = projectInfo;
    }

    public List<TestCaseRow> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCaseRow> testCases) {
        this.testCases = testCases;
    }
}
