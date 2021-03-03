package com.university.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.university.dto.StudentDTO;
import com.university.entity.Student;
import com.university.exception.BadRequestException;
import com.university.exception.ModelNotFoundException;
import com.university.service.StudentService;

@RestController
@RequestMapping(path = "/api/students")
public class StudentController {

	@Autowired
	StudentService studentService;
	
	
	@GetMapping
	public ResponseEntity<List<Student>> getStudents() throws Exception{
		List<Student> students = studentService.getStudents();
		return new ResponseEntity<List<Student>>(students,HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable("id") long id) throws Exception{
		Optional<Student> studentOptional = studentService.getStudent(id);
		if(!studentOptional.isPresent()) {
			throw new ModelNotFoundException("Student not found");
		}
		Student student = studentOptional.get();
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Student> createNewStudent(@RequestBody StudentDTO studentDTO) throws Exception {	
		 	if(studentService.existsByDni(studentDTO.getDni())) {
			 throw new BadRequestException("DNI is taken");
		 	}
			if(studentDTO.getFirstName().isBlank()) {
				   throw new BadRequestException("FirstName is required");
			}
			if(studentDTO.getLastName().isBlank()) {
				 throw new BadRequestException("LastName is required");
			}
			if(studentDTO.getDni().isBlank()) {
				 throw new BadRequestException("DNI is required");
			}
			Student student = new Student(studentDTO.getFirstName(),studentDTO.getLastName(),studentDTO.getDni());
			studentService.addNewStudent(student);
	        return new ResponseEntity<Student>(student, HttpStatus.CREATED);
	
		}
	
	
	}
	
