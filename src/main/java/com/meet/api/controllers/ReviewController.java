package com.meet.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meet.api.mapper.ReviewMapper;
import com.meet.api.model.Review;
import com.meet.api.model.dto.ReviewDto;
import com.meet.api.response.Response;
import com.meet.api.services.ReviewService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private ReviewMapper mapper;

	@GetMapping(value = "/restaurants/{restaurantId}/reviews")
	public ResponseEntity<List<ReviewDto>> findReviewsByRestaurantId(@PathVariable("restaurantId") Long restaurantId) {
		log.info("Starting get reviews by restaurantId {} ", restaurantId);
		List<ReviewDto> review = this.mapper.toListReviewDto(this.reviewService.findReviewsByRestaurantId(restaurantId));

		if (review.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(review, HttpStatus.OK);
	}

	@PostMapping(value = "/reviews")
	public ResponseEntity<Response<ReviewDto>> create(@Valid @RequestBody ReviewDto reviewDto, BindingResult result) {
		log.info("Starting create review...");
		Response<ReviewDto> response = new Response<>();

		if (result.hasErrors()) {
			log.error("Error invalid information for Review: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Review review = this.reviewService.persist(this.mapper.toReview(reviewDto));
		response.setData(this.mapper.toReviewDto(review));
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
