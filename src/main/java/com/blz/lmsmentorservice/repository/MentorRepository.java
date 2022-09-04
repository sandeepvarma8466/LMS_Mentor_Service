package com.blz.lmsmentorservice.repository;

import com.blz.lmsmentorservice.model.MentorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentorRepository extends JpaRepository<MentorModel, Long> {
    List<MentorModel> findByMentorRole(String mentorType);
}
