package com.university.dto;

import javax.validation.constraints.NotBlank;

public class TeacherDTO {
	
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	private String dni;
	@NotBlank
	private boolean active;
	
	public TeacherDTO(@NotBlank String firstName, 
			@NotBlank String lastName,
			@NotBlank String dni,
			@NotBlank boolean active) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dni = dni;
		this.active = active;
	}

	public String getFirstName() { return firstName; }

	public void setFirstName(String firstName) { this.firstName = firstName; }

	public String getLastName() { return lastName; }

	public void setLastName(String lastName) { this.lastName = lastName; }

	public String getDni() { return dni; }

	public void setDni(String dni) { this.dni = dni; }

	public boolean isActive() { return active; }

	public void setActive(boolean active) { this.active = active; }
	
	
	
	

}
