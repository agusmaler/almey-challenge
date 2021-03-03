package com.university.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.entity.Student;
import com.university.entity.Subject;

@Service
public class EnrollmentService {
	
	@Autowired
	StudentService studentService;
	@Autowired
	SubjectService subjectService;
	
	@Transactional
	 public boolean makeEnrollment(long studentId, long subjectId) throws Exception {
			
	        Optional<Student> studentOptional = studentService.getStudent(studentId);
	        
	        if(!studentOptional.isPresent()) return false; 
	        
	        Optional<Subject> subjectOptional = subjectService.getSubject(subjectId);
	        
	        if(!subjectOptional.isPresent()) return false;
	        
	        Subject subject = subjectOptional.get();
	        
	        boolean isFull = subject.isFull();
	        
	        if(isFull) return false;
	        
	        Student student = studentOptional.get();
	        
	        boolean isEnrolled = student.findSubject(subjectId);
	        
	        if(isEnrolled) return false;
	        
	        student.addSubject(subject);
	        return true;
	    }

}
