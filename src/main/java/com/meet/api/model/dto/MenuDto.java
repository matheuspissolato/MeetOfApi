package com.meet.api.model.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class MenuDto {

	private Long id;

	@NotEmpty(message = "Restaurant id cannot be empty")
	private Long restaurantId;

	@NotEmpty(message = "Name cannot be empty")
	@Length(min = 5, max = 50, message = "Name must contain between 5 and 50 characters")
	private String name;

	@NotEmpty(message = "Description cannot be empty")
	@Length(max = 100, message = "Description must contain 100 characters")
	private String description;

	@NotEmpty(message = "Image Path cannot be empty")
	@Length(max = 200, message = "Image Path must contain  200 characters")
	private String imagePath;

	@NotEmpty(message = "Price cannot be empty")
	private Double price;
}
