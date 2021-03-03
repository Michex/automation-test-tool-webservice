package com.toster.webservices.testconfig.model;

import com.toster.webservices.testsuite.model.TestSuite;

public class Config extends NewConfig {

    public final Long id;
    public final TestSuite testSuite;


    public Config(Long id, String path, TestSuite testSuite) {
        super(path);
        this.testSuite = testSuite;
        this.id = id;
    }


}
