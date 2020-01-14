package com.toster.tosterbackend.db;


import com.toster.tosterbackend.testCase.model.TestCase;
import com.toster.tosterbackend.testSuite.model.TestSuite;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class TestSuiteRow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String projectInfo;

    @OneToMany(mappedBy = "testSuiteRow", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TestCaseRow> testCases = new ArrayList<>();

    public TestSuiteRow(String projectName) {
        this.projectInfo = projectName;
    }

    public TestSuiteRow() {
    }

    public TestSuite toTestSuite(){
        return new TestSuite(
                this.getId(),
                this.getProjectInfo(),
                Optional.of(this.testCases).map(trl ->
                        io.vavr.collection.List.ofAll(trl).map(tr ->
                            new TestCase(tr.getId(), tr.getTestName(), tr.getTestSuiteRow().id))
                ).get().orElse(io.vavr.collection.List.empty()).asJava());
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
