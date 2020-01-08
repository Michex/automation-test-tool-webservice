package com.toster.tosterbackend.db;


import com.toster.tosterbackend.testSuite.model.TestSuite;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TestSuiteRow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String projectInfo;

    @OneToMany(mappedBy = "testSuiteRow")
    private List<TestCaseRow> testCases = new ArrayList<TestCaseRow>();

    public TestSuiteRow(String projectName) {
        this.projectInfo = projectName;
    }

    public TestSuiteRow() {
    }

    public TestSuite toTestSuite(){
        return new TestSuite(
                this.getId(),
                this.getProjectInfo(),
                io.vavr.collection.List.ofAll(this.getTestCases()).map(TestCaseRow::toTestCase).asJava()
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

    public List<TestCaseRow> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCaseRow> testCases) {
        this.testCases = testCases;
    }
}
