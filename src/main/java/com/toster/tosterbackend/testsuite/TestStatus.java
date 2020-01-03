package com.toster.tosterbackend.testsuite;

import lombok.Data;

@Data
public class TestStatus {

    private NewTestCase test;
    private String status;
    private String cause;


}
