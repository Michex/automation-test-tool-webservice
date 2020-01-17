package com.toster.tosterbackend.controllers;

import com.toster.tosterbackend.testRunner.TestStatusService;
import com.toster.tosterbackend.testRunner.model.NewTestStatus;
import com.toster.tosterbackend.testRunner.model.TestStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/testRunner")
public class TestRunnerController {

    private final TestStatusService testStatusService;

    public TestRunnerController(TestStatusService testStatusService) {
        this.testStatusService = testStatusService;
    }

    @RequestMapping(
            value = "/runTestSuite/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public List<TestStatus> runTestSuite(@PathVariable("id") long id) {
        return testStatusService.runTestSuite(id);
    }

    @RequestMapping(
            value = "/setTestStatus",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE

    )
    @ResponseBody
    public TestStatus setTestStatus(NewTestStatus newTestStatus) {
        return testStatusService.setTestStatusFromTestApp(newTestStatus);
    }







}
