package com.meet.api.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import com.meet.api.model.Restaurant;
import com.meet.api.model.dto.RestaurantDto;

@Component
public class RestaurantMapper {
	private static final ModelMapper mapper = new ModelMapper();

	public Restaurant toRestaurant(RestaurantDto restaurantDto) {
		return mapper.map(restaurantDto, Restaurant.class);
	}

	public RestaurantDto toRestaurantDto(Restaurant restaurant) {
		return mapper.map(restaurant, RestaurantDto.class);
	}

	public List<RestaurantDto> toListRestaurantDto(List<Restaurant> restaurant) {
		return mapper.map(restaurant, new TypeToken<List<Restaurant>>() {
		}.getType());
	}
}
