package com.toster.tosterbackend.controllers;

import com.toster.tosterbackend.testCase.TestCaseService;
import com.toster.tosterbackend.testCase.model.NewTestCase;
import com.toster.tosterbackend.testCase.model.TestCase;

import com.toster.tosterbackend.testSuite.model.TestSuite;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/testCase")
public class TestCaseController {

    private final TestCaseService testCaseService;

    public TestCaseController(TestCaseService testCaseService){
        this.testCaseService = testCaseService;
    }

    @RequestMapping(
            value = "/getAllTests",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public List<TestCase> getAllTests() {
        return testCaseService.getTests().asJava();
    }

    @RequestMapping(
            value = "/addTest",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public TestCase addTest(@RequestBody final NewTestCase testModels) {
        return testCaseService.addTest(testModels);
    }

    @RequestMapping(
            value = "/testSuite/{idTestSuite}/testCase/{idTestCase}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public TestCase setTestCaseToTestSuite(@PathVariable("idTestSuite") long idTestSuite, @PathVariable("idTestCase") long idTestCase) {
        return testCaseService.setTestCaseToTestSuite(idTestSuite, idTestCase).orElseThrow(
                () -> new IllegalArgumentException("Test Case of id: " + idTestCase + " does not exist")
        );
    }



//    @RequestMapping(
//            value = "/changeTestSuiteInTestCase",
//            method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE,
//            consumes = MediaType.APPLICATION_JSON_VALUE
//    )
//    @ResponseBody
//    public TestSuite changeTestSuiteInTestCase(@RequestBody final TestCase newTestSuite) {
//        return testCaseService.addTestCaseToTestSuite(newTestSuite);
//    }



}
