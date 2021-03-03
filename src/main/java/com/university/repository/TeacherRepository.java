package com.university.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.university.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	
	Optional<Teacher> findTeacherByDni(String dni) throws Exception;
	

	Optional<Teacher> findTeacherById(long id) throws Exception;

	boolean existsByDni(String dni) throws Exception;
	
	void deleteTeacherById(long id);

}
