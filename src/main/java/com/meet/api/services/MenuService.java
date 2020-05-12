package com.meet.api.services;

import java.util.List;

import com.meet.api.model.Menu;

public interface MenuService {

	/**
	 * Return all menu items for restaurant id
	 * 
	 * @param restaurantId
	 * @return
	 */
	List<Menu> findMenuByRestaurantId(Long restaurantId);

}
