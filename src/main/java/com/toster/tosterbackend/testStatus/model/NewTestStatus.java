package com.toster.tosterbackend.testStatus.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewTestStatus {

    public final String testCaseName;
    public final String status;
    public final String stackTrace;
    public final String runDate;



    @JsonCreator
    public NewTestStatus(@JsonProperty("testCaseName")String testCaseName,
                         @JsonProperty("status")String status,
                         @JsonProperty("stackTrace") String stackTrace,
                         @JsonProperty("runDate") String runDate) {

        this.testCaseName = testCaseName;
        this.status = status;
        this.stackTrace = stackTrace;
        this.runDate = runDate;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
