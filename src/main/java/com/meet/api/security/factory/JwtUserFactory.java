package com.meet.api.security.factory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.meet.api.enums.ProfileUser;
import com.meet.api.model.User;
import com.meet.api.security.model.JwtUser;

public class JwtUserFactory {

	private JwtUserFactory() {
	}

	/**
	 * Convert and create one JwtUser with information from user
	 * 
	 * @param funcionario
	 * @return JwtUser
	 */
	public static JwtUser create(User user) {
		return new JwtUser(user.getId(), user.getEmail(), user.getPassword(), mapToGrantedAuthorities(user.getProfile()));
	}

	/**
	 * Convert profile user to spring security format.
	 * 
	 * @param perfilEnum
	 * @return List<GrantedAuthority>
	 */
	private static List<GrantedAuthority> mapToGrantedAuthorities(ProfileUser perfilEnum) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));
		return authorities;
	}

}