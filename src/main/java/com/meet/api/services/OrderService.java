package com.meet.api.services;

import java.util.Optional;

import org.springframework.validation.annotation.Validated;

import com.meet.api.model.Order;

@Validated
public interface OrderService {

	/**
	 * Return Order by Id
	 * 
	 * @param id
	 * @return
	 */
	Optional<Order> findById(Long id);

	/**
	 * Persist Order
	 * 
	 * @param Order
	 * @return
	 */
	Order persist(Order order);
}
