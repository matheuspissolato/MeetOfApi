package com.meet.api.services.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.meet.api.model.User;
import com.meet.api.repository.UserRepository;
import com.meet.api.services.UserService;

@Service
public class UserImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<User> findByEmail(String email) {
		return Optional.ofNullable(this.userRepository.findByEmail(email));
	}

	@Override
	public User persist(User user) {
		return this.userRepository.save(user);
	}

	public void validateInformation(String email, BindingResult result) {
		this.findByEmail(email).ifPresent(func -> result.addError(new ObjectError("user", "Email Exist.")));
	}

}