package com.meet.api.model.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class OrderDto {

	private Long id;

	@NotNull(message = "Restaurant id cannot be empty")
	private Long restaurantId;

	@NotNull(message = "User id cannot be empty")
	private Long userId;

	@NotEmpty(message = "Payment option cannot be empty")
	private String paymentOption;

	@NotEmpty(message = "Name cannot be empty")
	@Length(min = 5, max = 50, message = "Name must contain between 5 and 50 characters")
	private String name;

	@NotEmpty(message = "Name cannot be empty")
	@Length(max = 100, message = "Email must contain max 100 characters")
	private String email;

	@NotEmpty(message = "Phone cannot be empty")
	@Length(min = 11, max = 11, message = "Phone number must contain 11 numbers.")
	private String phone;

	@NotEmpty(message = "Street cannot be empty")
	@Length(min = 5, max = 100, message = "Street must contain between 5 and 100 characters.")
	private String street;

	@NotNull(message = "Number cannot be empty")
	private Long number;

	private Double value;

	private String optionalAdress;

	private List<OrderItemDto> orderItems = new ArrayList<>();

}
