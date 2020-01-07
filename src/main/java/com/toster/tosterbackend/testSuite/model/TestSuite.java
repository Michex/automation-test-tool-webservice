package com.toster.tosterbackend.testSuite.model;


import com.toster.tosterbackend.testCase.model.TestCase;
import io.vavr.collection.List;


public class TestSuite extends NewTestSuite {

    public final long id;

    public TestSuite(long id, String projectName, List<TestCase> testCase) {
        super(projectName, testCase);
        this.id = id;
    }

}
