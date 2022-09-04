package com.blz.lmsmentorservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class MentorNotFoundException extends RuntimeException {
    private int statuscode;
    private String message;

    public MentorNotFoundException(String message, int statuscode) {
        super(message);
        this.statuscode = statuscode;
        this.message = message;
    }
}
