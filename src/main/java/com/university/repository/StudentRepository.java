package com.university.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.university.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	Optional<Student> findStudentByDni(String dni) throws Exception;
	
	Optional<Student> findStudentById(long id) throws Exception;
	
	void deleteStudentById(long id);

	boolean existsByDni(String dni) throws Exception;
}
