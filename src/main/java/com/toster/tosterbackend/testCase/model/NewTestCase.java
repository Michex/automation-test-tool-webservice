package com.toster.tosterbackend.testCase.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.toster.tosterbackend.testSuite.model.TestSuite;

public class NewTestCase {

    public final String testName;
    public final long testSuiteId;

    @JsonCreator
    public NewTestCase(@JsonProperty("testName") String testName, @JsonProperty("testCase") long testSuiteId) {
        this.testName = testName;
        this.testSuiteId = testSuiteId;
    }

}
