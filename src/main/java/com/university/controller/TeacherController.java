package com.university.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.university.dto.TeacherDTO;
import com.university.entity.Teacher;
import com.university.exception.BadRequestException;
import com.university.exception.ModelNotFoundException;
import com.university.service.TeacherService;

@RestController
@RequestMapping(path = "/api/teachers")
public class TeacherController {
	
	@Autowired
	TeacherService teacherService;
	
	public ResponseEntity<List<Teacher>> getTeachers() throws Exception {
		List<Teacher> teachers = teacherService.getTeachers();
		return new ResponseEntity<List<Teacher>>(teachers,HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Teacher> getStudent(@PathVariable("id") long id) throws Exception{
		 Teacher teacher = teacherService.getTeacher(id);
			
		 if(teacher == null) {
			 throw new ModelNotFoundException("Subject not found");
		 }
		 return new ResponseEntity<Teacher>(teacher,HttpStatus.OK);
	}
	
	 @PostMapping
	 public ResponseEntity<Teacher> createNewSubject(@RequestBody TeacherDTO teacherDTO) throws Exception{
		 if(teacherService.existsByDni(teacherDTO.getDni())) {
			 throw new BadRequestException("DNI is taken");
		 }
		 if(teacherDTO.getFirstName().isBlank()) {
			 throw new BadRequestException("FirstName is required");
		 }
		 if(teacherDTO.getLastName().isBlank()) {
			 throw new BadRequestException("LastName is required");
		 }
		 if(teacherDTO.getDni().isBlank()) {
			 throw new BadRequestException("DNI is required");
		 }

		 Teacher teacher = new Teacher(teacherDTO.getFirstName(),teacherDTO.getLastName(),teacherDTO.getDni(),teacherDTO.isActive());
		 teacherService.addNewTeacher(teacher);
		 return new ResponseEntity<Teacher>(teacher,HttpStatus.CREATED);
	 }

}
