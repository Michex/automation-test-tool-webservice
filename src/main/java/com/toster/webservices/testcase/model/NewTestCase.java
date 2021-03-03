package com.toster.webservices.testcase.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewTestCase {

    public final String testName;


    @JsonCreator
    public NewTestCase(@JsonProperty("testName") String testName) {
        this.testName = testName;
    }

}
