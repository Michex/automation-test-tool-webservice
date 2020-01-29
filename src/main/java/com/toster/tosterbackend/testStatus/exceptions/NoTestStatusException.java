package com.toster.tosterbackend.testStatus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoTestStatusException extends RuntimeException {

    public NoTestStatusException(String date) {
        super("Test Statuses from  " + date + " does not exist");
    }

}
