package com.university.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.entity.Student;
import com.university.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	
	
	public List<Student> getStudents() throws Exception {
		return studentRepository.findAll();
	}
	
	public Optional<Student> getStudent(String dni) throws Exception {
		return studentRepository.findStudentByDni(dni);
	}
	
	public Optional<Student> getStudent(long id) throws Exception {
		return studentRepository.findStudentById(id);
	}
	
	 public boolean existsById(long id) throws Exception {
	        return studentRepository.existsById(id);
	    }
	 
	 public boolean existsByDni(String dni) throws Exception {
	        return studentRepository.existsByDni(dni);
	    }
	
	public Student addNewStudent(Student student) throws Exception {
		return studentRepository.save(student);
	}
	
	public void deleteStudent(long studentId) throws Exception {
		studentRepository.deleteStudentById(studentId);
	}
	
	
	
	
	
	
	

}
