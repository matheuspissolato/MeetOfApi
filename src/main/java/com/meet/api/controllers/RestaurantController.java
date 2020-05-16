package com.meet.api.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meet.api.mapper.RestaurantMapper;
import com.meet.api.model.Restaurant;
import com.meet.api.model.dto.RestaurantDto;
import com.meet.api.response.Response;
import com.meet.api.services.RestaurantService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private RestaurantMapper mapper;

	@GetMapping("/restaurants")
	public ResponseEntity<List<RestaurantDto>> findAll() {
		log.info("Starting get all restaurants");
		List<RestaurantDto> restaurantDto = this.mapper.toListRestaurantDto(restaurantService.findAll());

		if (restaurantDto.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(restaurantDto, HttpStatus.OK);
	}

	@GetMapping(value = "/restaurants/{id}")
	public ResponseEntity<RestaurantDto> findById(@PathVariable("id") Long id) {
		log.info("Starting get restaurant by id {}", id);
		Optional<Restaurant> restaurant = this.restaurantService.findById(id);

		if (restaurant.isPresent())
			return new ResponseEntity<>(this.mapper.toRestaurantDto(restaurant.get()), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/restaurants")
	public ResponseEntity<Response<RestaurantDto>> create(@Valid @RequestBody RestaurantDto restaurantDto,
			BindingResult result) {
		log.info("Starting create Restaurant...");
		Response<RestaurantDto> response = new Response<>();

		if (result.hasErrors()) {
			log.error("Error invalid information for Restaurant: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Restaurant restaurant = this.restaurantService.persist(this.mapper.toRestaurant(restaurantDto));
		response.setData(this.mapper.toRestaurantDto(restaurant));
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping("/restaurants")
	public ResponseEntity<Response<RestaurantDto>> update(@Valid @RequestBody RestaurantDto restaurantDto,
			BindingResult result) {
		log.info("Starting update Restaurant...");
		Response<RestaurantDto> response = new Response<>();

		if (result.hasErrors()) {
			log.error("Error invalid information for Restaurant: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Restaurant restaurant = this.restaurantService.persist(this.mapper.toRestaurant(restaurantDto));
		response.setData(this.mapper.toRestaurantDto(restaurant));
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/restaurants/{id}")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") Long id) {
		log.info("Starting delete Restaurant by id {}", id);
		Response<String> response = new Response<>();
		Optional<Restaurant> restaurant = this.restaurantService.findById(id);

		if (!restaurant.isPresent()) {
			log.info("Error delete Restaurant by id {}", id);
			response.getErrors().add("Restaurant cannot found by id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		this.restaurantService.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response<String>());

	}

}
