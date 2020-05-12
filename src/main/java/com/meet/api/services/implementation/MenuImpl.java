package com.meet.api.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.meet.api.model.Menu;
import com.meet.api.repository.MenuRepository;
import com.meet.api.services.MenuService;

@Service
public class MenuImpl implements MenuService {

	@Autowired
	private MenuRepository menuRepository;

	@Override
	@Cacheable("restaurantMenu")
	public List<Menu> findMenuByRestaurantId(Long restaurantId) {
		return this.menuRepository.findMenuByRestaurantId(restaurantId);
	}
}
