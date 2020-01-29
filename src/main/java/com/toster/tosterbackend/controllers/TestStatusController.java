package com.toster.tosterbackend.controllers;

import com.toster.tosterbackend.testRunner.TestRunnerService;
import com.toster.tosterbackend.testStatus.TestStatusService;
import com.toster.tosterbackend.testStatus.model.NewTestStatus;
import com.toster.tosterbackend.testStatus.model.TestStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/testStatus")
public class TestStatusController {

    private final TestStatusService testStatusService;

    public TestStatusController(TestStatusService testStatusService) {
        this.testStatusService = testStatusService;
    }

    @RequestMapping(
            value = "/setTestStatus",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE

    )
    @ResponseBody
    public TestStatus setTestStatus(NewTestStatus newTestStatus) {
        return testStatusService.setTestStatusFromTestApp(newTestStatus);
    }

    @RequestMapping(
            value = "/getTestStatuses/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public List<TestStatus> getTestStatuses(@PathVariable("id") long id) {
        return testStatusService.getTestStatuses(id);
    }









}
