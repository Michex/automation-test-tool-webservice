package com.toster.tosterbackend.testSuite.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoTestSuiteException extends RuntimeException {

    public NoTestSuiteException(long id){
        super("Test Suite of id: " + id + " does not exist");
    }

}
