package com.meet.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.meet.api.model.Menu;

@Repository
@Transactional(readOnly = true)
public interface MenuRepository extends JpaRepository<Menu, Long> {
	
	List<Menu> findMenuByRestaurantId(Long restaurantId);

}
