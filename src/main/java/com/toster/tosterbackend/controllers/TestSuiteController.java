package com.toster.tosterbackend.controllers;

import com.toster.tosterbackend.testSuite.model.*;
import com.toster.tosterbackend.testSuite.TestSuiteService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/testSuite")
public class TestSuiteController {


    private final TestSuiteService testSuiteService;

    public TestSuiteController(TestSuiteService testService) {
        this.testSuiteService = testService;
    }


    @RequestMapping(
            value = "/addTestSuite",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public TestSuite addTestSuite(@RequestBody final NewTestSuite newTestSuite) {
        return testSuiteService.addTestSuite(newTestSuite);
    }


    @RequestMapping(
            value = "/getTestSuites",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public List<TestSuite> getTestSuites() {
        return testSuiteService.getTestSuites().asJava();
    }

    @RequestMapping(
            value = "/getTestSuite/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public TestSuite getTestSuite(@PathVariable("id") long id) {
        return testSuiteService.getTestSuite(id);
    }


}

