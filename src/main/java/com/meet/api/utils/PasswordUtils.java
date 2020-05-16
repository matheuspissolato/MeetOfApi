package com.meet.api.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

	public PasswordUtils() {
	}

	/**
	 * Creating is hash using BCrypt.
	 * 
	 * @param password
	 * @return String
	 */
	public static String generateBCrypt(String senha) {
		if (senha == null) {
			return senha;
		}

		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		return bCryptEncoder.encode(senha);
	}

}
