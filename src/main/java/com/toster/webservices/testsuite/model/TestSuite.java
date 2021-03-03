package com.toster.webservices.testsuite.model;


import com.toster.webservices.testcase.model.TestCase;

import java.util.List;


public class TestSuite extends NewTestSuite {

    public final long id;
    public final List<TestCase> testCases;

    public TestSuite(long id, String projectName, List<TestCase> testCases) {
        super(projectName);
        this.id = id;
        this.testCases = testCases;
    }

}
