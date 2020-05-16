package com.meet.api.controllers;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meet.api.mapper.UserMapper;
import com.meet.api.model.User;
import com.meet.api.model.dto.UserDto;
import com.meet.api.response.Response;
import com.meet.api.services.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserMapper mapper;

	public UserController() {
	}

	/**
	 * Create one User
	 * 
	 * @param userDto
	 * @param result
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping("/users")
	public ResponseEntity<Response<UserDto>> create(@Valid @RequestBody UserDto userDto, BindingResult result) {
		log.info("Starting persist User...");
		Response<UserDto> response = new Response<>();

		this.userService.validateInformation(userDto.getEmail(), result);

		if (result.hasErrors()) {
			log.error("Error invalid information for User: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		User user = this.userService.persist(this.mapper.toUser(userDto));
		response.setData(this.mapper.toUserDto(user));
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
