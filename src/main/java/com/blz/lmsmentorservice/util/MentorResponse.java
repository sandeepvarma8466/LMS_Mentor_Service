package com.blz.lmsmentorservice.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MentorResponse {
    private int errorcode;
    private String message;
    private Object token;
}
