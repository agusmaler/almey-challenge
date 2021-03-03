package com.university.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity(name = "Student")
@Table(
        name = "student",
        uniqueConstraints = {
                @UniqueConstraint(name = "student_dni_unique", columnNames = "dni")
        }
)
public class Student {
	
	@Id
	@SequenceGenerator(name = "student_sequence",sequenceName = "student_sequence",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
	@Column(name = "id")
	private long id;
	
	@NotBlank
	@Column(name = "first_name")
	private String firstName;
	
	@NotBlank
	@Column(name = "last_name")
	private String lastName;
	
	@NotBlank
	@Column(name = "dni")
	private String dni;
	
	@JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="enrollment", 
    joinColumns = @JoinColumn (name="id_student"),
    inverseJoinColumns= @JoinColumn(name="id_subject"))
    private Set<Subject> subjects;
	
	
	public Student() {
		subjects = new HashSet<Subject>();
	}

	public Student(@NotBlank String firstName, 
			@NotBlank String lastName,
			@NotBlank String dni
			) {
		this();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dni = dni;
	}

	public long getId() { return id; }

	public void setId(long id) { this.id = id; }

	public String getFirstName() { return firstName; }

	public void setFirstName(String firstName) { this.firstName = firstName; }

	public String getLastName() { return lastName; }

	public void setLastName(String lastName) { this.lastName = lastName; }

	public String getDni() { return dni; }

	public void setDni(String dni) { this.dni = dni; }
	
	public Set<Subject> getSubjects() { return subjects; }
	
	public void addSubject(Subject subject) {
		if(subject.getCapacity() > 0) {
			subjects.add(subject);
			if(!subject.getStudents().contains(this)) {
				subject.addStudent(this);
			}	
		}	
	}
	
	public void removeSubject(Subject subject) {
		if(subjects.contains(subject)) {
			subjects.remove(subject);
			if(subject.getStudents().contains(this)) {
				subject.removeStudent(this);
			}
		}
	}
	
	public boolean findSubject(Long subjectId) {
		  boolean isEnrolled = subjects.stream().filter(sb -> sb.getId() == subjectId)
	                .findFirst().isPresent();
		  if (isEnrolled)
	            return true;
	        else
	            return false;
	}


	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dni=" + dni + "]";
	}

}
