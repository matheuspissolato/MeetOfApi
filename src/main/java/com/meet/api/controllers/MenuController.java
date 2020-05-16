package com.meet.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meet.api.mapper.MenuMapper;
import com.meet.api.model.dto.MenuDto;
import com.meet.api.services.MenuService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class MenuController {

	@Autowired
	private MenuService menuService;

	@Autowired
	private MenuMapper mapper;

	@GetMapping(value = "/restaurants/{restaurantId}/menus")
	public ResponseEntity<List<MenuDto>> findMenuByRestaurant(@PathVariable("restaurantId") Long restaurantId) {
		log.info("Starting get menu items by restaurantId");
		List<MenuDto> menu = this.mapper.toListMenuDto(this.menuService.findMenuByRestaurantId(restaurantId));

		if (menu.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(menu, HttpStatus.OK);
	}

}
