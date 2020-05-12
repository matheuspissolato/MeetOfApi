package com.meet.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "order_item")
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 9188047189455343918L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "menu_id", nullable = false)
	private Long menuId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties("orderItems")
	private Order order;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "quantity", nullable = false)
	private Double quantity;

	@Column(name = "price", nullable = false)
	private Double price;

	public OrderItem() {
	}

	public OrderItem(Long menuId, String description, Double quantity, Double price) {
		this.menuId = menuId;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
	}

}
