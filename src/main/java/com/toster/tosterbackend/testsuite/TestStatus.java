package com.toster.tosterbackend.testsuite;

import lombok.Data;


@Data
public class TestStatus {

    private String testName;
    private String status;
    private String cause;


}
