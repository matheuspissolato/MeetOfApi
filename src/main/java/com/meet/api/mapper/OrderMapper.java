package com.meet.api.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import com.meet.api.model.Order;
import com.meet.api.model.OrderItem;
import com.meet.api.model.dto.OrderDto;

@Component
public class OrderMapper {
	private static final ModelMapper mapper = new ModelMapper();

	public OrderDto toOrderDto(Order order) {
		return mapper.map(order, OrderDto.class);
	}

	public Order toOrder(OrderDto orderDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new PropertyMap<OrderDto, Order>() {
			@Override
			protected void configure() {
				skip(source.getOrderItems(), destination.getOrderItems());
			}
		});

		Order order = modelMapper.map(orderDto, Order.class);
		orderDto.getOrderItems().forEach(item -> order
				.addItems(new OrderItem(item.getMenuId(), item.getDescription(), item.getQuantity(), item.getPrice())));
		return order;
	}
}
