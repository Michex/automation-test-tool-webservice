package com.toster.webservices.testrunner;

import com.toster.webservices.teststatus.model.TestStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/run")
public class TestRunnerController {

    private final TestRunnerService testRunnerService;

    public TestRunnerController(TestRunnerService testRunnerService) {
        this.testRunnerService = testRunnerService;
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public List<TestStatus> runTestSuite(@PathVariable("id") long id) {
        return testRunnerService.runTestSuite(id);
    }



}
