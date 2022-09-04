package com.blz.lmsmentorservice.controller;

import com.blz.lmsmentorservice.dto.MentorDTO;
import com.blz.lmsmentorservice.model.MentorModel;
import com.blz.lmsmentorservice.service.IMentorService;
import com.blz.lmsmentorservice.util.MentorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/mentormodule")
public class MentorController {
    @Autowired
    IMentorService mentorService;

    @PostMapping("/addmentor")
    public ResponseEntity<MentorResponse> addMentor(@Valid @RequestBody MentorDTO mentorDTO, @RequestHeader String token) {
        MentorModel mentorModel = mentorService.addMentor(mentorDTO, token);
        MentorResponse mentorResponse = new MentorResponse(200, "mentor inserted successfully", mentorModel);
        return new ResponseEntity<>(mentorResponse, HttpStatus.OK);
    }

    @PutMapping("/updatementor/{id}")
    public ResponseEntity<MentorResponse> updateMentor(@PathVariable("id") Long id, @Valid @RequestBody MentorDTO mentorDTO,
                                                       @RequestHeader String token) {
        MentorModel mentorModel = mentorService.updateMentor(id, mentorDTO, token);
        MentorResponse mentorResponse = new MentorResponse(200, "mentor updated successfully", mentorModel);
        return new ResponseEntity<>(mentorResponse, HttpStatus.OK);
    }

    @GetMapping("/fetchmentorsdetails")
    public ResponseEntity<MentorResponse> fetchMentorsDetails(@RequestHeader String token) {
        List<MentorModel> mentorModel = mentorService.fetchMentorsDetails(token);
        MentorResponse mentorResponse = new MentorResponse(200, "mentor details fetched successfully", mentorModel);
        return new ResponseEntity<>(mentorResponse, HttpStatus.OK);
    }

    @GetMapping("/fetchMentorBy/{id}")
    public ResponseEntity<MentorResponse> fetchMentorById(@PathVariable("id") Long id, @RequestHeader String token) {
        MentorModel mentorModel = mentorService.fetchMentorById(id, token);
        MentorResponse mentorResponse = new MentorResponse(200, "mentor details fetched by id successfully", mentorModel);
        return new ResponseEntity<>(mentorResponse, HttpStatus.OK);
    }

    @DeleteMapping("/deletementor/{id}")
    public ResponseEntity<MentorResponse> deleteMentor(@PathVariable("id") Long id, @RequestHeader String token) {
        MentorModel mentorModel = mentorService.deleteMentor(id, token);
        MentorResponse mentorResponse = new MentorResponse(200, "mentor deleted successfully", mentorModel);
        return new ResponseEntity<>(mentorResponse, HttpStatus.OK);
    }

    @GetMapping("/getmentorbyfields")
    public ResponseEntity<MentorResponse> getMentorByFields(@RequestParam String mentorRole, @RequestHeader String token) {
        List<MentorModel> mentorModel = mentorService.getMentorByFields(mentorRole, token);
        MentorResponse mentorResponse = new MentorResponse(200, "mentor details fetched by feild successfully", mentorModel);
        return new ResponseEntity<>(mentorResponse, HttpStatus.OK);
    }

    @GetMapping("/getcountbymentorrole")
    public ResponseEntity<MentorResponse> getCountByMentorRole(@RequestParam String mentorRole, @RequestHeader String token) {
        Long count = mentorService.getCountByMentorRole(mentorRole, token);
        MentorResponse mentorResponse = new MentorResponse(200, "get mentor count by mentor role successfully", count);
        return new ResponseEntity<>(mentorResponse, HttpStatus.OK);
    }
}
