package com.toster.tosterbackend.testSuite.model;


import com.toster.tosterbackend.testCase.model.TestCase;

import java.util.List;


public class TestSuite extends NewTestSuite {

    public final long id;

    public TestSuite(long id, String projectName, List<TestCase> testCases) {
        super(projectName, testCases);
        this.id = id;
    }

}
