package com.university.dto;

import javax.validation.constraints.NotBlank;

public class StudentDTO {
	
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	private String dni;
	
	public StudentDTO(
			@NotBlank String firstName,
			@NotBlank String lastName,
			@NotBlank String dni) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dni = dni;
	}

	public String getFirstName() { return firstName; }

	public void setFirstName(String firstName) { this.firstName = firstName; }

	public String getLastName() { return lastName; }

	public void setLastName(String lastName) { this.lastName = lastName; }

	public String getDni() { return dni; }

	public void setDni(String dni) { this.dni = dni; }
	
	
	
	
	

}
