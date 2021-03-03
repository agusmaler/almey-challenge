package com.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.entity.Teacher;
import com.university.repository.TeacherRepository;

@Service
public class TeacherService {
	
	@Autowired
	TeacherRepository teacherRepository;
	
	
	public List<Teacher> getTeachers() throws Exception {
		return teacherRepository.findAll();
	}
	
	public Teacher getTeacher(String dni) throws Exception {
		return teacherRepository.findTeacherByDni(dni).orElseThrow(() 
				-> new IllegalStateException("student with dni: " + dni + " does not exist"));
	}
	
	public Teacher getTeacher(long id) throws Exception {
		return teacherRepository.findTeacherById(id).orElseThrow(() 
				-> new IllegalStateException("teacher with dni: " + id + " does not exist"));
	}
	
	 public boolean existsById(long id) throws Exception {
	        return teacherRepository.existsById(id);
	    }
	 
	 public boolean existsByDni(String dni) throws Exception {
	        return teacherRepository.existsByDni(dni);
	    }
	
	public void addNewTeacher(Teacher teacher) throws Exception {
		teacherRepository.save(teacher);
	}
	
	public void deleteTeacher(long teacherId) throws Exception {
		teacherRepository.deleteTeacherById(teacherId);
	}
	
	

}
