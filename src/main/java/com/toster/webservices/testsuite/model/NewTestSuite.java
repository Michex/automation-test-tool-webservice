package com.toster.webservices.testsuite.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class NewTestSuite {

    public final String projectName;


    @JsonCreator
    public NewTestSuite(@JsonProperty("projectName") String projectName) {
        this.projectName = projectName;

    }



}
