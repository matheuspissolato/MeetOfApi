package com.meet.api.services;

import java.util.Optional;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import com.meet.api.model.User;

@Validated
public interface UserService {

	/**
	 * Return one User by email
	 * 
	 * @param email
	 * @return
	 */
	Optional<User> findByEmail(String email);

	/**
	 * Persist a new User
	 * 
	 * @param user
	 * @return
	 */
	User persist(User user);

	/**
	 * Check if user exists in database
	 * 
	 * @param userDto
	 * @param result
	 */
	void validateInformation(String email, BindingResult result);
}
