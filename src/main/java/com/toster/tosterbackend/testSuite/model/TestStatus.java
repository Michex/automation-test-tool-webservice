package com.toster.tosterbackend.testSuite.model;

import lombok.Data;


@Data
public class TestStatus {

    private String testName;
    private String status;
    private String cause;


}