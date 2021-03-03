package com.university.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.university.dto.SubjectDTO;
import com.university.entity.Subject;
import com.university.exception.BadRequestException;
import com.university.exception.ModelNotFoundException;
import com.university.service.SubjectService;

@RestController
@RequestMapping(path = "/api/subjects")
public class SubjectController {
	
	 @Autowired
	 SubjectService subjectService;
	 
	 @GetMapping
	 public ResponseEntity<List<Subject>> getSubjects() throws Exception{
		 List<Subject> subjects = subjectService.getSubjects();
		 return new ResponseEntity<List<Subject>>(subjects,HttpStatus.OK);
	 }
	 
	 @GetMapping(path = "/{id}")
	 public ResponseEntity<Subject> getSubject(@PathVariable("id") long id) throws Exception{
		 Optional<Subject> subjectOptional = subjectService.getSubject(id);
	
		 if(!subjectOptional.isPresent()) {
			 throw new ModelNotFoundException("Subject not found");
		 }
		 
		 Subject subject = subjectOptional.get();
		 return new ResponseEntity<Subject>(subject,HttpStatus.OK);
	 }
	 
	 @PostMapping
	 public ResponseEntity<Subject> createNewSubject(@RequestBody SubjectDTO subjectDTO) throws Exception{
		 if(subjectService.existsByName(subjectDTO.getName())) {
			 throw new BadRequestException("Name is taken");
		 }
		 if(subjectDTO.getName().isBlank()) {
			 throw new BadRequestException("Name is required");
		 }
		 if(subjectDTO.getSchedule().isEmpty()) {
			 throw new BadRequestException("Schedule is required");
		 }
		 if(subjectDTO.getCapacity() <=0) {
			 throw new BadRequestException("Capacity invalid");
		 }
		 Subject subject = new Subject(subjectDTO.getName(),subjectDTO.getSchedule(),subjectDTO.getTeacher(),subjectDTO.getCapacity());
		 subjectService.addNewSubject(subject);
		 return new ResponseEntity<Subject>(subject,HttpStatus.CREATED);
	 }
	 
	 @PutMapping(path = "/update/{id}")
	 public Subject updateSubject(@PathVariable("id") long id, @RequestBody SubjectDTO subjectDTO) throws Exception {
		 return subjectService.updateSubject(id, subjectDTO);
	 }

}
