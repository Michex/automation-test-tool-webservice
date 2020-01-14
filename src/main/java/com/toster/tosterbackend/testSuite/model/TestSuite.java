package com.toster.tosterbackend.testSuite.model;


import com.toster.tosterbackend.testCase.model.TestCase;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public class TestSuite extends NewTestSuite {

    public final long id;
    public final List<TestCase> testCases;

    public TestSuite(long id, String projectName, List<TestCase> testCases) {
        super(projectName);
        this.id = id;
        this.testCases = testCases;
    }

}
