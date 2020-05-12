package com.meet.api.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import com.meet.api.model.User;
import com.meet.api.model.dto.UserDto;
import com.meet.api.utils.PasswordUtils;

@Component
public class UserMapper {

	private static final ModelMapper mapper = new ModelMapper();

	public UserDto toUserDto(User user) {
		return mapper.map(user, UserDto.class);
	}

	public User toUser(UserDto userDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new PropertyMap<UserDto, User>() {
			@Override
			protected void configure() {
				map().setPassword(PasswordUtils.generateBCrypt(source.getPassword()));
			}
		});

		return modelMapper.map(userDto, User.class);
	}
}
