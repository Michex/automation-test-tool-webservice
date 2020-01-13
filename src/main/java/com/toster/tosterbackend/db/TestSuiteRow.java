package com.toster.tosterbackend.db;


import com.toster.tosterbackend.testSuite.model.TestSuite;

import javax.persistence.*;
import java.util.Optional;
import java.util.Set;

@Entity
public class TestSuiteRow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String projectInfo;

    @OneToMany(mappedBy = "testSuiteRow")
    private Set<TestCaseRow> testCases;

    public TestSuiteRow(String projectName) {
        this.projectInfo = projectName;
    }

    public TestSuiteRow() {
    }

    public TestSuite toTestSuite(){
        return new TestSuite(
                this.getId(),
                this.getProjectInfo()
        );
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

    public Set<TestCaseRow> getTestCases() {
        return testCases;
    }

    public void setTestCases(Set<TestCaseRow> testCases) {
        this.testCases = testCases;
    }
}
