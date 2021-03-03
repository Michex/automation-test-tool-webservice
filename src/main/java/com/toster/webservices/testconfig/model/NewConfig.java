package com.toster.webservices.testconfig.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewConfig {

    public final String path;

    @JsonCreator
    public NewConfig(@JsonProperty("path") String path) {
        this.path = path;
    }


}
