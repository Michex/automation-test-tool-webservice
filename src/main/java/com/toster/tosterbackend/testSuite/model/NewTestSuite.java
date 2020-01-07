package com.toster.tosterbackend.testSuite.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.toster.tosterbackend.testCase.model.TestCase;

import io.vavr.collection.List;


public class NewTestSuite {

    public final String projectName;
    public List<TestCase> testCases;

    @JsonCreator
    public NewTestSuite(@JsonProperty("projectName") String projectName) {
        this.projectName = projectName;
    }

    @JsonCreator
    public NewTestSuite(@JsonProperty("projectName") String projectName, List<TestCase> testCases) {
        this.projectName = projectName;
        this.testCases = testCases;
    }


}
