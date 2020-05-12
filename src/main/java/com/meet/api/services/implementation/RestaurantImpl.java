package com.meet.api.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.meet.api.model.Restaurant;
import com.meet.api.repository.RestaurantRepository;
import com.meet.api.services.RestaurantService;

@Service
public class RestaurantImpl implements RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Override
	@Cacheable("restaurants")
	public List<Restaurant> findAll() {
		return this.restaurantRepository.findAll();
	}

	@Override
	@Cacheable("restaurants")
	public Optional<Restaurant> findById(Long id) {
		return this.restaurantRepository.findById(id);
	}

	@Override
	@CachePut("restaurants")
	public Restaurant persist(Restaurant restaurant) {
		return this.restaurantRepository.save(restaurant);
	}

	@Override
	@CachePut("restaurants")
	public void deleteById(Long id) {
		this.restaurantRepository.deleteById(id);
	}

}
