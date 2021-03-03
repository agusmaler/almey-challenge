package com.university;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.university.service.EnrollmentService;
import com.university.service.StudentService;
import com.university.service.SubjectService;

@RestController
@RequestMapping(path = "/api/enrollment")
public class EnrollmentController {
	
	@Autowired
	SubjectService subjectServie;
	@Autowired
	StudentService studentService;
	@Autowired
	EnrollmentService enrollmentService;
	
	@PostMapping(path = "{subjectId}/{studentId}")
	public boolean enroll(@PathVariable("subjectId") long subjectId, @PathVariable("studentId") long studentId) throws Exception {
		return enrollmentService.makeEnrollment(studentId, subjectId);		
	}
	

}
