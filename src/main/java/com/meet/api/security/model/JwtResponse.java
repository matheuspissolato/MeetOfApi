package com.meet.api.security.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;

	private String email;
	private String accessToken;

	public JwtResponse(String email, String accessToken) {
		this.email = email;
		this.accessToken = accessToken;
	}

	public JwtResponse() {
	}
}