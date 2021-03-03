package com.university.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.university.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
	
	Optional<Subject> findSubjectByNameIgnoreCase(String name) throws Exception;
	
	Optional<Subject> findSubjectById(long id) throws Exception;
	
	boolean existsByNameIgnoreCase(String name) throws Exception;
	
	void deleteSubjectById(long id);

}
