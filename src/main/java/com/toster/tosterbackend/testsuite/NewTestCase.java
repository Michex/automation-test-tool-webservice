package com.toster.tosterbackend.testsuite;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewTestCase {

    public final String appName;
    public final String testName;

    @JsonCreator
    public NewTestCase(@JsonProperty("appName") String appName,
                       @JsonProperty("testName") String testName) {

        this.appName = appName;
        this.testName = testName;
    }

}
