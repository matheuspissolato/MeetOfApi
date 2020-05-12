package com.meet.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.meet.api.model.Review;

@Repository
@Transactional(readOnly = true)
public interface ReviewRepository extends JpaRepository<Review, Long> {

	List<Review> findReviewsByRestaurantId(Long restaurantId);

}
