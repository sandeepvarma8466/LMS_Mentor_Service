package com.blz.lmsmentorservice.service;

import com.blz.lmsmentorservice.dto.MentorDTO;
import com.blz.lmsmentorservice.exception.MentorNotFoundException;
import com.blz.lmsmentorservice.model.MentorModel;
import com.blz.lmsmentorservice.repository.MentorRepository;
import com.blz.lmsmentorservice.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MentorService implements IMentorService {
    @Autowired
    MentorRepository mentorRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    MailService mailService;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public MentorModel addMentor(MentorDTO mentorDTO, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8068/adminmodule/validateuser/" + token,
                Boolean.class);
        if (isUserPresent) {
            MentorModel mentorModel = new MentorModel(mentorDTO);
            mentorRepository.save(mentorModel);
            String body = "Mentor Added Successfully with Mentor Id" + mentorModel.getId();
            String subject = "Mentor Added successfully";
            mailService.send(mentorModel.getEmail(), subject, body);
            return mentorModel;
        }
        throw new MentorNotFoundException("Invalid Token", 500);
    }

    @Override
    public MentorModel updateMentor(Long id, MentorDTO mentorDTO, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8068/adminmodule/validateuser/" + token,
                Boolean.class);
        if (isUserPresent) {
            Optional<MentorModel> isIdPresent = mentorRepository.findById(id);
            if (isIdPresent.isPresent()) {
                isIdPresent.get().setEmployeeId(mentorDTO.getEmployeeId());
                isIdPresent.get().setFirstName(mentorDTO.getFirstName());
                isIdPresent.get().setLastName(mentorDTO.getLastName());
                isIdPresent.get().setMentorType(mentorDTO.getMentorType());
                isIdPresent.get().setMentorRole(mentorDTO.getMentorRole());
                isIdPresent.get().setMobileNumber(mentorDTO.getMobileNumber());
                isIdPresent.get().setEmail(mentorDTO.getEmail());
                isIdPresent.get().setExperienceYears(mentorDTO.getExperienceYears());
                isIdPresent.get().setPreferredTime(mentorDTO.getPreferredTime());
                isIdPresent.get().setStartDate(LocalDate.now());
                isIdPresent.get().setStatus(mentorDTO.getStatus());
                isIdPresent.get().setMentorDescription(mentorDTO.getMentorDescription());
                isIdPresent.get().setProfileImageURL(mentorDTO.getProfileImageURL());
                isIdPresent.get().setCreatorUser(mentorDTO.getCreatorUser());
                isIdPresent.get().setSupervisorId(mentorDTO.getSupervisorId());
                isIdPresent.get().setCreatedTimeStamp(LocalDateTime.now());
                isIdPresent.get().setUpdatedTimeStamp(LocalDateTime.now());
                mentorRepository.save(isIdPresent.get());
                String body = "Mentor updated Successfully with Mentor Id" + isIdPresent.get().getId();
                String subject = "Mentor Updated successfully";
                mailService.send(isIdPresent.get().getEmail(), subject, body);
                return isIdPresent.get();
            }
            throw new MentorNotFoundException("Mentor Id Not Found", 500);
        }
        throw new MentorNotFoundException("Invalid Token", 500);
    }

    @Override
    public List<MentorModel> fetchMentorsDetails(String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8068/adminmodule/validateuser/" + token,
                Boolean.class);
        if (isUserPresent) {
            List<MentorModel> isMentorsPresent = mentorRepository.findAll();
            if (isMentorsPresent.size() > 0) {
                return isMentorsPresent;
            }
            throw new MentorNotFoundException("Mentor Id Not Found", 500);
        }
        throw new MentorNotFoundException("Invalid Token", 500);
    }

    @Override
    public MentorModel fetchMentorById(Long id, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8068/adminmodule/validateuser/" + token,
                Boolean.class);
        if (isUserPresent) {
            Optional<MentorModel> isIdPresent = mentorRepository.findById(id);
            if (isIdPresent.isPresent()) {
                return isIdPresent.get();
            }
            throw new MentorNotFoundException("Mentor Id Not Found", 500);
        }
        throw new MentorNotFoundException("Invalid Token", 500);
    }

    @Override
    public MentorModel deleteMentor(Long id, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8068/adminmodule/validateuser/" + token,
                Boolean.class);
        if (isUserPresent) {
            Optional<MentorModel> isIdPresent = mentorRepository.findById(id);
            if (isIdPresent.isPresent()) {
                mentorRepository.delete(isIdPresent.get());
                String body = "Mentor Deleted Successfully with Mentor Id" + isIdPresent.get().getId();
                String subject = "Mentor Delete successfully";
                mailService.send(isIdPresent.get().getEmail(), subject, body);
                return isIdPresent.get();
            }
            throw new MentorNotFoundException("Mentor Id Not Found", 500);
        }
        throw new MentorNotFoundException("Invalid Token", 500);
    }

    @Override
    public List<MentorModel> getMentorByFields(String mentorRole, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8068/adminmodule/validateuser/" + token,
                Boolean.class);
        if (isUserPresent) {
            List<MentorModel> isMentorTypePresent = mentorRepository.findByMentorRole(mentorRole);
            if (isMentorTypePresent.size() > 0) {
                return isMentorTypePresent;
            }
            throw new MentorNotFoundException("Invalid MentorRole", 500);
        }
        throw new MentorNotFoundException("Invalid Token", 500);
    }

    @Override
    public long getCountByMentorRole(String mentorRole, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8068/adminmodule/validateuser/" + token,
                Boolean.class);
        if (isUserPresent) {
            List<MentorModel> isMentorPresent = mentorRepository.findByMentorRole(mentorRole);
            if (isMentorPresent.size() > 0) {
                return isMentorPresent.stream().count();
            }
            throw new MentorNotFoundException("Invalid MentorRole", 500);
        }
        throw new MentorNotFoundException("Invalid Token", 500);
    }
}

