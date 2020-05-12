package com.meet.api.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.meet.api.mapper.OrderMapper;
import com.meet.api.model.Order;
import com.meet.api.model.dto.OrderDto;
import com.meet.api.response.Response;
import com.meet.api.services.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderMapper mapper;

	@GetMapping("/order/{id}")
	public ResponseEntity<OrderDto> findById(@PathVariable("id") Long id, RedirectAttributes attributes) {
		log.info("Starting get Order by id {}", id);

		Optional<Order> order = this.orderService.findById(id);

		if (order.isPresent())
			return new ResponseEntity<>(this.mapper.toOrderDto(order.get()), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/order")
	public ResponseEntity<Response<OrderDto>> create(@Valid @RequestBody OrderDto orderDto, BindingResult result) {
		log.info("Starting persist Order...");
		Response<OrderDto> response = new Response<>();

		if (result.hasErrors()) {
			log.error("Error invalid information for Order: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Order order = this.orderService.persist(this.mapper.toOrder(orderDto));
		response.setData(this.mapper.toOrderDto(order));
		return ResponseEntity.ok(response);
	}
}
