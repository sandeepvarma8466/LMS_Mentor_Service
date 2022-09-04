package com.blz.lmsmentorservice.model;

import com.blz.lmsmentorservice.dto.MentorDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "mentor_details")
@Data
@NoArgsConstructor
public class MentorModel {
    @Id
    @GenericGenerator(name = "mentor_details", strategy = "increment")
    @GeneratedValue(generator = "mentor_details")
    private Long Id;
    private String employeeId;
    private String firstName;
    private String lastName;
    private String mentorType;
    private String mentorRole;
    private String mobileNumber;
    private String email;
    private String experienceYears;
    private String preferredTime;
    private LocalDate startDate;
    private String status;
    private String mentorDescription;
    private String profileImageURL;
    private int creatorUser;
    private long supervisorId;
    @JsonIgnore
    private LocalDateTime createdTimeStamp;
    @JsonIgnore
    private LocalDateTime updatedTimeStamp;

    public MentorModel(MentorDTO mentorDTO) {
        this.employeeId = mentorDTO.getEmployeeId();
        this.firstName = mentorDTO.getFirstName();
        this.lastName = mentorDTO.getLastName();
        this.mentorType = mentorDTO.getMentorType();
        this.mentorRole = mentorDTO.getMentorRole();
        this.mobileNumber = mentorDTO.getMobileNumber();
        this.email = mentorDTO.getEmail();
        this.experienceYears = mentorDTO.getExperienceYears();
        this.preferredTime = mentorDTO.getPreferredTime();
        this.startDate = mentorDTO.getStartDate();
        this.status = mentorDTO.getStatus();
        this.mentorDescription = mentorDTO.getMentorDescription();
        this.profileImageURL = mentorDTO.getProfileImageURL();
        this.creatorUser = mentorDTO.getCreatorUser();
        this.supervisorId = mentorDTO.getSupervisorId();
        this.createdTimeStamp = LocalDateTime.now();
        this.updatedTimeStamp = LocalDateTime.now();
    }
}

