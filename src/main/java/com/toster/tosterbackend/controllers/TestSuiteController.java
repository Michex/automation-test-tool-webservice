package com.toster.tosterbackend.controllers;

import com.toster.tosterbackend.testsuite.NewTestCase;
import com.toster.tosterbackend.testsuite.TestCase;
import com.toster.tosterbackend.testsuite.TestStatus;
import com.toster.tosterbackend.testsuite.TestSuiteService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/testSuite")
public class TestSuiteController {


    private final TestSuiteService testService;

    public TestSuiteController(TestSuiteService testService) {
        this.testService = testService;
    }

    @RequestMapping(
            value = "/getTests",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public java.util.List<TestCase> getTests() {
        return testService.getTests();
    }

    @RequestMapping(
            value = "/addTest",
            method = RequestMethod.POST
    )
    @ResponseBody
    public TestCase addTest(final NewTestCase testModels) {
        return testService.addTest(testModels);
    }

    @RequestMapping(
            value = "/runTests",
            method = RequestMethod.GET
    )

    @ResponseBody
    public TestStatus runTest(final TestCase testCase) throws IOException {
        return testService.runTest(testCase);
    }
}

