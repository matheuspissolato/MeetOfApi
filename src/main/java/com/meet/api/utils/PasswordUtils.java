package com.meet.api.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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

		log.info("Creating hash with BCrypt.");
		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		return bCryptEncoder.encode(senha);
	}

}
