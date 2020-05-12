package com.meet.api.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.meet.api.model.User;
import com.meet.api.security.factory.JwtUserFactory;
import com.meet.api.services.UserService;

@Component
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String email) {
		Optional<User> user = this.userService.findByEmail(email);

		if (user.isPresent()) {
			return JwtUserFactory.create(user.get());
		}
		throw new UsernameNotFoundException("Email not found");
	}

}