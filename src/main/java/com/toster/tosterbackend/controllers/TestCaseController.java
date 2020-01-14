package com.toster.tosterbackend.controllers;

import com.toster.tosterbackend.testCase.TestCaseService;
import com.toster.tosterbackend.testCase.model.NewTestCase;
import com.toster.tosterbackend.testCase.model.TestCase;

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
            value = "/addTest/{idTestSuite}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public TestCase addTest(@RequestBody @PathVariable("idTestSuite") long idTestSuite, final NewTestCase testModels) {
        return testCaseService.addTest(idTestSuite, testModels);
    }





}
