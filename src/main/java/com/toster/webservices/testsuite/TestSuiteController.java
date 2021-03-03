package com.toster.webservices.testsuite;

import com.toster.webservices.testsuite.model.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/suites")
public class TestSuiteController {

    private final TestSuiteService testSuiteService;

    public TestSuiteController(TestSuiteService testService) {
        this.testSuiteService = testService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public TestSuite addTestSuite(@RequestBody final NewTestSuite newTestSuite) {
        return testSuiteService.addTestSuite(newTestSuite);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public List<TestSuite> getTestSuites() {
        return testSuiteService.getTestSuites().asJava();
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public TestSuite getTestSuite(@PathVariable("id") long id) {
        return testSuiteService.getTestSuite(id);
    }
}
