package com.toster.tosterbackend.testSuite.model;


import com.toster.tosterbackend.testCase.model.TestCase;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public class TestSuite extends NewTestSuite {

    public final long id;

    public TestSuite(long id, String projectName) {
        super(projectName);
        this.id = id;
    }

}
