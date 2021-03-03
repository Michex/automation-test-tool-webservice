package com.toster.webservices.teststatus;

import com.toster.webservices.teststatus.model.NewTestStatus;
import com.toster.webservices.teststatus.model.TestStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statuses")
public class TestStatusController {

    private final TestStatusService testStatusService;

    public TestStatusController(TestStatusService testStatusService) {
        this.testStatusService = testStatusService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE

    )
    @ResponseBody
    public TestStatus setTestStatus(@RequestBody NewTestStatus newTestStatus) {
        return testStatusService.setTestStatusFromTestApp(newTestStatus);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public List<TestStatus> getTestStatusesByTestCase(@PathVariable("id") long id) {
        return testStatusService.getTestStatusesByTestCase(id);
    }

    @RequestMapping(
            value = "/{date}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public List<TestStatus> getTestStatusesByDate(@PathVariable("date") String date) {
        return testStatusService.getTestStatusesByDate(date);
    }

}
