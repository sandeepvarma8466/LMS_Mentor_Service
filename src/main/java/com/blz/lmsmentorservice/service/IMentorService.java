package com.blz.lmsmentorservice.service;

import com.blz.lmsmentorservice.dto.MentorDTO;
import com.blz.lmsmentorservice.model.MentorModel;

import java.util.List;

public interface IMentorService {
    MentorModel addMentor(MentorDTO mentorDTO, String token);
    MentorModel updateMentor(Long id, MentorDTO mentorDTO, String token);
    List<MentorModel> fetchMentorsDetails(String token);
    MentorModel fetchMentorById(Long id, String token);
    MentorModel deleteMentor(Long id, String token);
    List<MentorModel> getMentorByFields(String mentorRole, String token);
    long getCountByMentorRole(String mentorRole, String token);
}

