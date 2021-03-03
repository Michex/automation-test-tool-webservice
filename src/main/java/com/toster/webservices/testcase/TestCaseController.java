package com.toster.webservices.testcase;

import com.toster.webservices.testcase.model.NewTestCase;
import com.toster.webservices.testcase.model.TestCase;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cases")
public class TestCaseController {

    private final TestCaseService testCaseService;

    public TestCaseController(TestCaseService testCaseService){
        this.testCaseService = testCaseService;
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public List<TestCase> getAllTests() {
        return testCaseService.getTests().asJava();
    }

    @RequestMapping(
            value = "/{idTestSuite}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public TestCase addTest(@RequestBody @PathVariable("idTestSuite") final long idTestSuite, final NewTestCase testModels) {
        return testCaseService.addTest(idTestSuite, testModels);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public TestCase getTest(@RequestBody @PathVariable("id") final long id) {
        return testCaseService.getTest(id);
    }
}
