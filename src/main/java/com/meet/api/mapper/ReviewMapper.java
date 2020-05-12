package com.meet.api.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import com.meet.api.model.Review;
import com.meet.api.model.dto.ReviewDto;

@Component
public class ReviewMapper {

	private ModelMapper mapper = new ModelMapper();

	public List<ReviewDto> toListReviewDto(List<Review> review) {
		return mapper.map(review, new TypeToken<List<Review>>() {
		}.getType());
	}
}
