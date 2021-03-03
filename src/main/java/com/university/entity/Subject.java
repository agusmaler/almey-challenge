package com.university.entity;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "Subject")
@Table(name = "subject")
public class Subject {
	
	@Id
	@SequenceGenerator(name = "subject_sequence",sequenceName = "subject_sequence",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subject_sequence")
	@Column(name = "id")
	private long id;
	
	
	@NotBlank
	@Column(name = "name")
	private String name;
	
	@NotBlank
	@Column(name = "schedule")
	private String schedule;
	
	@ManyToOne
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "teacher_subject_fk"
            )
    )
	private Teacher teacher;
	
	@JsonIgnore
    @ManyToMany(mappedBy = "subjects", cascade = CascadeType.ALL)
    private Set<Student> students;
	
	@NotNull
	@Min(1)
	@Column(name = "capacity")
	private int capacity;
	
	@NotNull
	@Column(name = "enrollments")
	private int enrollments;
	
	
	public Subject() {
		 students = new HashSet<Student>();
	}
	
	public Subject(@NotBlank String name, 
			@NotBlank String schedule,
			@NotNull Teacher teacher,
			@NotNull @Min(1) int capacity
			) {
		this();
		this.name = name;
		this.schedule = schedule;
		this.teacher = teacher;
		this.capacity = capacity;
	}
	

	public long getId() { return id; }

	public void setId(long id) { this.id = id; }

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public String getSchedule() { return schedule; }

	public void setSchedule(String schedule) { this.schedule = schedule; }

	public Teacher getTeacher() { return teacher; }

	public void setTeacher(Teacher teacher) { this.teacher = teacher; }

	public int getCapacity() { return capacity; }

	public void setCapacity(int capacity) { this.capacity = capacity; }
		
	public Set<Student> getStudents() { return students; }

	@Override
	public String toString() {
		return "Subject [name=" + name + ", schedule=" + schedule + ", teacher=" + teacher + ", capacity=" + capacity
				+ "]";
	}

	public void subtractEnrollment() {
		this.enrollments-=1;
	}
	
	public void addEnrollment() {
		this.enrollments+=1;
	}
	
	public void addStudent(Student student) {
		if(!isFull()) {
			students.add(student);	
			addEnrollment();
			if(!student.getSubjects().contains(this)) {
				student.addSubject(this);
			}
		}
	}
	
	public void removeStudent(Student student) {
		if(students.contains(student)) {
			students.remove(student);
			subtractEnrollment();
			if(!student.getSubjects().contains(this)) {
				student.removeSubject(this);
			}
		}
	}
	
	public boolean isFull() {
		return this.capacity == this.enrollments;
	}
	
	
    
	
}
