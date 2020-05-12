package com.meet.api.services.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meet.api.model.Order;
import com.meet.api.repository.OrderRepository;
import com.meet.api.services.OrderService;

@Service
public class OrderImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Optional<Order> findById(Long id) {
		return this.orderRepository.findById(id);
	}

	@Override
	public Order persist(Order order) {
		return this.orderRepository.save(order);
	}
}
