package com.toster.webservices.testsuite;

import com.toster.webservices.testsuite.db.TestSuiteRepository;
import com.toster.webservices.testsuite.db.TestSuiteRow;

import com.toster.webservices.testsuite.exceptions.NoTestSuiteException;
import com.toster.webservices.testsuite.model.*;
import io.vavr.collection.List;
import org.springframework.stereotype.Service;


@Service
public class TestSuiteService {

    private final TestSuiteRepository testSuiteRepository;

    public TestSuiteService(TestSuiteRepository testSuiteRepository) {
        this.testSuiteRepository = testSuiteRepository;
    }

    public TestSuite addTestSuite(NewTestSuite newTestSuite) {
        return this.testSuiteRepository.save(new TestSuiteRow(newTestSuite.projectName)).toTestSuite();
    }

    public List<TestSuite> getTestSuites() {
        return List.ofAll(this.testSuiteRepository.findAll())
                .map(TestSuiteRow::toTestSuite);
    }

    public TestSuite getTestSuite(long id) {
        return this.testSuiteRepository.findById(id).orElseThrow(() -> new NoTestSuiteException(id)).toTestSuite();
    }

}
