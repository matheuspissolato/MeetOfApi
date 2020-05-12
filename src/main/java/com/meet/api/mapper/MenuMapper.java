package com.meet.api.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import com.meet.api.model.Menu;
import com.meet.api.model.dto.MenuDto;

@Component
public class MenuMapper {

	private ModelMapper mapper = new ModelMapper();

	public List<MenuDto> toListMenuDto(List<Menu> menu) {
		return mapper.map(menu, new TypeToken<List<Menu>>() {
		}.getType());
	}

}
