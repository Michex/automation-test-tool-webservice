package com.toster.tosterbackend.controllers;

import com.toster.tosterbackend.testRunner.TestRunnerService;
import com.toster.tosterbackend.testStatus.model.NewTestStatus;
import com.toster.tosterbackend.testStatus.model.TestStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/testRunner")
public class TestRunnerController {

    private final TestRunnerService testRunnerService;

    public TestRunnerController(TestRunnerService testRunnerService) {
        this.testRunnerService = testRunnerService;
    }

    @RequestMapping(
            value = "/runTestSuite/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public List<TestStatus> runTestSuite(@PathVariable("id") long id) {
        return testRunnerService.runTestSuite(id);
    }



}
