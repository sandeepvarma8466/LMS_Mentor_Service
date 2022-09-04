package com.blz.lmsmentorservice.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class MentorDTO {
    @NotBlank(message = "employeeId cannot be blank")
    private String employeeId;
    @Pattern(regexp = "^[A-Z][a-z]{2,}$", message = "Mentor FirstName Invalid")
    private String firstName;
    @Pattern(regexp = "^[A-Z][a-z]{2,}$", message = "Mentor LastName Invalid")
    private String lastName;
    @NotBlank(message = "mentortype cannot be empty")
    private String mentorType;
    @NotBlank(message = "mentor role cannot be empty")
    private String mentorRole;
    @Pattern(regexp = "^[1-9]{2}\\s{1}[1-9]{1}[0-9]{9}$", message = "Invalid Mobile Number")
    private String mobileNumber;
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid Email Id")
    private String email;
    @NotBlank(message = "experience years cannot br empty")
    private String experienceYears;
    @NotBlank(message = "preferred time cannot be null")
    private String preferredTime;
    @NotNull(message = "start date cannot be empty")
    private LocalDate startDate;
    @NotNull(message = "Status cannot be empty")
    private String status;
    @NotNull(message = "mentor description cannot be empty")
    private String mentorDescription;
    @NotBlank(message = "profilr image url cannot be empty")
    private String profileImageURL;
    private int creatorUser;
    @NotBlank(message = "supervisor id cannot be empty")
    private long supervisorId;
}

