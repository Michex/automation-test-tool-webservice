package com.toster.tosterbackend.controllers;

import com.toster.tosterbackend.testCase.TestCaseService;
import com.toster.tosterbackend.testCase.model.NewTestCase;
import com.toster.tosterbackend.testCase.model.TestCase;
import com.toster.tosterbackend.testSuite.model.*;
import com.toster.tosterbackend.testSuite.TestSuiteService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/testSuite")
public class TestSuiteController {


    private final TestSuiteService testSuiteService;

    public TestSuiteController(TestSuiteService testService) {
        this.testSuiteService = testService;
    }


    @RequestMapping(
            value = "/addTestSuite",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public TestSuite addTestSuite(@RequestBody final NewTestSuite newTestSuite) {
        return testSuiteService.addTestSuite(newTestSuite);
    }






//    @RequestMapping(
//            value = "/runTestSuite",
//            method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE,
//            consumes = MediaType.APPLICATION_JSON_VALUE
//    )
//    @ResponseBody
//    public TestStatus runTestSuite() throws IOException {
//        return testService.runTest(testSuite);
//    }
}

