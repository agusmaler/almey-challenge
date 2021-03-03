package com.university;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.university.entity.Student;
import com.university.entity.Subject;
import com.university.entity.Teacher;
import com.university.service.StudentService;
import com.university.service.SubjectService;
import com.university.service.TeacherService;

@SpringBootApplication
public class AlkemyChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlkemyChallengeApplication.class, args);
	}
	

	@Bean
	CommandLineRunner commandLineRunner(StudentService studentService,
										SubjectService subjectService, 
										TeacherService teacherService) {
		 return args -> {
			
			 Teacher profe1 = new Teacher("Michael","Ron","405869765",true);
			 Teacher profe2 = new Teacher("Gabriel","Alisson","3505869765",true);
			 teacherService.addNewTeacher(profe1);
			 teacherService.addNewTeacher(profe2);
					 
			 Subject matematicas = new Subject("Matematicas 1","20hs",profe1,20);
			 Subject fisica = new Subject("Fisica 1","20hs",profe2,15);
			 
			 Student jhon = new Student("jhon", "taylor","39758459");
			 Student tomas = new Student("Tomas", "Rodriguez","48885758459");
			 Student matias = new Student("Matias", "Rodriguez","425758459");
			 
			 matematicas.addStudent(jhon);
			 matematicas.addStudent(tomas);
			 fisica.addStudent(matias);
			 
			 studentService.addNewStudent(jhon);
			 studentService.addNewStudent(tomas);
			 studentService.addNewStudent(matias);
			 
		 };
	}
	
	
	

}
