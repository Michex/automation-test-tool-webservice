package com.toster.tosterbackend.testRunner.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewTestStatus {

    public final long testCaseId;
    public final String status;
    public final String stackTrace;
    public final String runDate;



    @JsonCreator
    public NewTestStatus(@JsonProperty("testCaseId")long testCaseId,
                         @JsonProperty("status")String status,
                         @JsonProperty("stackTrace") String stackTrace,
                         @JsonProperty("runDate") String runDate) {

        this.testCaseId = testCaseId;
        this.status = status;
        this.stackTrace = stackTrace;
        this.runDate = runDate;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
