package com.toster.tosterbackend.testCase.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoTestCaseException extends RuntimeException {

    public NoTestCaseException(long id) {
        super("Test Suite of id: " + id + " does not exist");
    }

    public NoTestCaseException(String name) {
        super("Test Suite of name: " + name + " does not exist");
    }
}
