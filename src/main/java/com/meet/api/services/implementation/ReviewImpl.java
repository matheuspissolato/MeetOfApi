package com.meet.api.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.meet.api.model.Review;
import com.meet.api.repository.ReviewRepository;
import com.meet.api.services.ReviewService;

@Service
public class ReviewImpl implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	@Override
	@Cacheable("restaurantReview")
	public List<Review> findReviewsByRestaurantId(Long restaurantId) {
		return this.reviewRepository.findReviewsByRestaurantId(restaurantId);
	}
}
