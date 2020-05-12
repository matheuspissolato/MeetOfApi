package com.meet.api.model.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class ReviewDto {

	private Long id;

	@NotNull(message = "Restaurant id cannot be empty")
	private Long restaurantId;

	@NotNull(message = "User id cannot be empty")
	private Long userId;

	@NotEmpty(message = "Name cannot be empty")
	@Length(max = 100, message = "Name must contain 100 characters")
	private String name;

	@NotNull(message = "Rating cannot be empty")
	private Double rating;

	@NotEmpty(message = "Comments cannot be empty")
	@Length(max = 200, message = "Comments must contain  200 characters")
	private String comments;

	private LocalDateTime created;
}
