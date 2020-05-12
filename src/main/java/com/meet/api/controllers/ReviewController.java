package com.meet.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meet.api.mapper.ReviewMapper;
import com.meet.api.model.dto.ReviewDto;
import com.meet.api.services.ReviewService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private ReviewMapper mapper;

	@GetMapping(value = "/restaurants/{restaurantId}/review")
	public ResponseEntity<List<ReviewDto>> findReviewsByRestaurantId(@PathVariable("restaurantId") Long restaurantId) {
		log.info("Starting get reviews by restaurantId {} ", restaurantId);
		List<ReviewDto> review = this.mapper.toListReviewDto(this.reviewService.findReviewsByRestaurantId(restaurantId));

		if (review.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(review, HttpStatus.OK);

	}

}
