package com.meet.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.validation.annotation.Validated;

import com.meet.api.model.Restaurant;

@Validated
public interface RestaurantService {

	/**
	 * Return list restaurant
	 * 
	 * @param pageRequest
	 * @return
	 */
	List<Restaurant> findAll();

	/**
	 * Return restaurant
	 * 
	 * @param id
	 * @return
	 */
	Optional<Restaurant> findById(Long id);

	/**
	 * Persist new Restaurant
	 * 
	 * @param restaurant
	 * @return
	 */
	Restaurant persist(Restaurant restaurant);

	/**
	 * Delete Restaurant by id
	 * 
	 * @param id
	 */
	void deleteById(Long id);

}
