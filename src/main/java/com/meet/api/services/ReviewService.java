package com.meet.api.services;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.meet.api.model.Review;

@Validated
public interface ReviewService {

	/**
	 * Return all reviews for restaurant id
	 * 
	 * @param restaurantId
	 * @return
	 */
	List<Review> findReviewsByRestaurantId(Long restaurantId);

	/**
	 * Persist Review
	 * 
	 * @param review
	 * @return
	 */
	Review persist(Review review);

}
