package com.blz.lmsmentorservice.exception.exceptionHandler;

import com.blz.lmsmentorservice.exception.MentorNotFoundException;
import com.blz.lmsmentorservice.util.MentorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class MentorExceptionHandler {
    @ExceptionHandler(MentorNotFoundException.class)
    public ResponseEntity<MentorResponse> response(MentorNotFoundException mentorNotFoundException) {
        MentorResponse mentorResponce = new MentorResponse();
        mentorResponce.setErrorcode(400);
        mentorResponce.setMessage(mentorNotFoundException.getMessage());
        return new ResponseEntity<>(mentorResponce, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<MentorResponse>
    defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        MentorResponse mentorResponce = new MentorResponse();
        mentorResponce.setErrorcode(500);
        mentorResponce.setMessage(e.getMessage());
        return new ResponseEntity<MentorResponse>(mentorResponce, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
