package com.university.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.dto.SubjectDTO;
import com.university.entity.Subject;
import com.university.repository.SubjectRepository;

@Service
public class SubjectService {
	
	@Autowired
	SubjectRepository subjectRepository;
	
	
	public List<Subject> getSubjects() throws Exception {
		return subjectRepository.findAll();
	}
	
	public Optional<Subject> getSubject(long id) throws Exception {
		return subjectRepository.findSubjectById(id);
	}
	
	public boolean existsById(long id) throws Exception {
		return subjectRepository.existsById(id);
	}
	
	public boolean existsByName(String name) throws Exception {
		return subjectRepository.existsByNameIgnoreCase(name);
	}
	
	public Subject addNewSubject(Subject subject) throws Exception {
		return subjectRepository.save(subject);
	}
	
	@Transactional
	public Subject updateSubject(long subjectId, SubjectDTO subjectDTO) throws Exception {
		Subject subject = subjectRepository.findSubjectById(subjectId).orElseThrow(() 
				-> new IllegalStateException("subject with dni: " + subjectId + " does not exist"));
		
		if(!subjectDTO.getName().isBlank() && !Objects.equals(subject.getName(), subjectDTO.getName()) && !existsByName(subjectDTO.getName())) {
			subject.setName(subjectDTO.getName());
		}
		if(!subjectDTO.getSchedule().isBlank() && !Objects.equals(subject.getSchedule(), subjectDTO.getSchedule())) {
			subject.setSchedule(subjectDTO.getSchedule());
		}
		if(subjectDTO.getCapacity() > 0 && !Objects.equals(subject.getCapacity(), subjectDTO.getCapacity())) {
			subject.setCapacity(subjectDTO.getCapacity());
		}
		if(!Objects.equals(subject.getTeacher(), subjectDTO.getTeacher())) {
			subject.setTeacher(subjectDTO.getTeacher());
		}
		return subject;
	}
	
	public void deleteSubject(long subjectId) throws Exception {
		subjectRepository.deleteSubjectById(subjectId);
	}

}
