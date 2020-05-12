package com.meet.api.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meet.api.response.Response;
import com.meet.api.security.model.JwtRequest;
import com.meet.api.security.model.JwtResponse;
import com.meet.api.security.service.JwtUserDetailsService;
import com.meet.api.security.util.JwtTokenUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	/**
	 * Created and return a new token
	 * 
	 * @param authenticationDto
	 * @param result
	 * @return ResponseEntity<Response<TokenDto>>
	 * @throws AuthenticationException
	 */
	@PostMapping
	public ResponseEntity<Response<JwtResponse>> authenticate(@Valid @RequestBody JwtRequest jwtRequest, BindingResult result) {
		log.info("Creating token for email {}.", jwtRequest.getEmail());
		
		Response<JwtResponse> response = new Response<>();

		if (result.hasErrors()) {
			log.error("Error invalid information for User: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Authentication auth = manager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(auth);

		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getEmail());

		String token = jwtTokenUtil.generateToken(userDetails);
		response.setData(new JwtResponse(userDetails.getUsername(), token));

		return ResponseEntity.ok(response);
	}
}