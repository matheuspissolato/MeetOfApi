package com.meet.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meet.api.mapper.MenuMapper;
import com.meet.api.model.Menu;
import com.meet.api.model.dto.MenuDto;
import com.meet.api.response.Response;
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

	@PostMapping(value = "/menus")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER')")
	public ResponseEntity<Response<MenuDto>> create(@Valid @RequestBody MenuDto menuDto, BindingResult result) {
		log.info("Starting create a new Menu", menuDto.toString());
		Response<MenuDto> response = new Response<>();

		if (result.hasErrors()) {
			log.error("Error invalid information for Menu: {} ", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Menu menu = this.menuService.persist(this.mapper.toMenu(menuDto));
		response.setData(this.mapper.toMenuDto(menu));
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
