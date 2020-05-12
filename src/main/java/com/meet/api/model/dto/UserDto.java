package com.meet.api.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserDto {

	private Long id;

	@NotEmpty(message = "Name cannot be empty")
	@Length(min = 5, max = 100, message = "Name must contain between 5 and 100 characters.")
	private String name;

	@NotEmpty(message = "Email cannot be empty")
	@Length(min = 5, max = 200, message = "Email must contain between 5 and 200 characters.")
	@Email(message = "Email Invalid.")
	private String email;

	@NotEmpty(message = "Password cannot be empty")
	@Length(min = 4, max = 50, message = "Password must contain between 4 and 50 characters.")
	private String password;
}