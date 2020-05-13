package com.meet.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.meet.api.model.User;
import com.meet.api.model.dto.UserDto;

@Component
public class UserMapper {

	private static final ModelMapper mapper = new ModelMapper();

	public UserDto toUserDto(User user) {
		return mapper.map(user, UserDto.class);
	}

	public User toUser(UserDto userDto) {
		return mapper.map(userDto, User.class);
	}
}
