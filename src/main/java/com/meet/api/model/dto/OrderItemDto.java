package com.meet.api.model.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class OrderItemDto {

	private Long id;

	private Long menuId;

	@NotEmpty(message = "Description cannot be empty")
	@Length(min = 5, max = 200, message = "Description must contain between 5 and 200 characters")
	private String description;

	@NotEmpty(message = "Quantity cannot be empty")
	private Double quantity;

	@NotEmpty(message = "Price cannot be empty")
	private Double price;
}
