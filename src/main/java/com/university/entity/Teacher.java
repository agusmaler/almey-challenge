package com.university.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;


@Entity(name = "Teacher")
@Table(
        name = "teacher",
        uniqueConstraints = {
                @UniqueConstraint(name = "teacher_dni_unique", columnNames = "dni")
        }
)
public class Teacher {
	
	@Id
	@SequenceGenerator(name = "teacher_sequence",sequenceName = "teacher_sequence",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_sequence")
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
	
	@Column(name = "active")
	private boolean active;
	
	public Teacher() {
		
	}

	public Teacher(@NotBlank String firstName,
			@NotBlank String lastName,
			@NotBlank String dni,
			boolean active
			) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dni = dni;
		this.active = active;
	}

	public long getId() { return id; }

	public void setId(long id) { this.id = id; }

	public String getFirstName() { return firstName;
	}

	public void setFirstName(String firstName) { this.firstName = firstName; }

	public String getLastName() { return lastName; }

	public void setLastName(String lastName) { this.lastName = lastName; }

	public String getDni() { return dni; }

	public void setDni(String dni) { this.dni = dni; }

	public boolean isActive() { return active; }

	public void setActive(boolean active) { this.active = active; }

	@Override
	public String toString() {
		return "Teacher [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
	
	

}
