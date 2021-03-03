package com.university.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.university.entity.Teacher;

public class SubjectDTO {
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String schedule;
	
	@NotNull
	@Min(1)
	private int capacity;
	
	private Teacher teacher;

	public SubjectDTO(@NotBlank String name,
			@NotBlank String schedule,
			@NotNull @Min(1) int capacity,
			Teacher teacher) {
		super();
		this.name = name;
		this.schedule = schedule;
		this.capacity = capacity;
		this.teacher = teacher;
	}

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public String getSchedule() { return schedule; }

	public void setSchedule(String schedule) { this.schedule = schedule; }

	public int getCapacity() { return capacity; }

	public void setCapacity(int capacity) { this.capacity = capacity; }

	public Teacher getTeacher() { return teacher; }

	public void setTeacher(Teacher teacher) { this.teacher = teacher; }

}	
