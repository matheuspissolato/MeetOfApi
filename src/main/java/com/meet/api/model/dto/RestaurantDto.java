package com.meet.api.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class RestaurantDto {

	private long id;

	@NotEmpty(message = "Name cannot be empty")
	@Length(min = 5, max = 100, message = "Name must contain between 5 and 100 characters.")
	private String name;

	@NotEmpty(message = "Phone cannot be empty")
	@Length(min = 11, max = 11, message = "Phone number must contain 11 numbers.")
	private String phone;

	@NotEmpty(message = "Category cannot be empty")
	@Length(min = 5, max = 20, message = "Category must contain between 5 and 20 characters.")
	private String category;

	@NotEmpty(message = "Delivery estimate cannot be empty")
	@Length(min = 3, max = 5, message = "Delivery estimate must contain between 3 and 5 characters.")
	private String deliveryEstimate;

	@NotEmpty(message = "About cannot be empty")
	@Length(min = 10, max = 200, message = "About must contain between 10 and 200 characters.")
	private String about;

	@NotEmpty(message = "Hours cannot be empty")
	@Length(min = 10, max = 50, message = "Hours must contain between 10 and 50 characters.")
	private String hours;

	@NotEmpty(message = "Street cannot be empty")
	@Length(min = 5, max = 100, message = "Street must contain between 5 and 100 characters.")
	private String street;

	@NotEmpty(message = "District cannot be empty")
	@Length(max = 30, message = "District must contain between 10 and 30 characters.")
	private String district;

	@NotEmpty(message = "City cannot be empty")
	@Length(max = 25, message = "City must contain between 5 and 25 characters.")
	private String city;

	@NotEmpty(message = "State cannot be empty")
	@Length(min = 2, max = 20, message = "State must contain between 2 and 20 characters.")
	private String state;

	@NotEmpty(message = "Country cannot be empty")
	@Length(min = 5, max = 200, message = "Country must contain between 5 and 15 characters.")
	private String country;

	@NotEmpty(message = "Image cannot be empty")
	private String imagePath;

	@NotNull(message = "Delivery Cost cannot be empty")
	private Double deliveryCost;
}
