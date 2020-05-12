package com.meet.api.model.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class ReviewDto {

	private Long id;

	@NotEmpty(message = "Restaurant id cannot be empty")
	private Long restaurantId;

	@NotEmpty(message = "User id cannot be empty")
	private Long userId;

	@NotEmpty(message = "Name cannot be empty")
	@Length(max = 100, message = "Name must contain 100 characters")
	private String name;

	@NotEmpty(message = "Rating cannot be empty")
	private Double rating;

	@NotEmpty(message = "Comments cannot be empty")
	@Length(max = 200, message = "Comments must contain  200 characters")
	private String comments;

	@NotEmpty(message = "Create Date cannot be empty")
	private LocalDateTime created;
}
