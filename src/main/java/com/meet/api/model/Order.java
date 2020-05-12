package com.meet.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "`order`")
@EqualsAndHashCode(exclude = { "orderItems" })
public class Order implements Serializable {

	private static final long serialVersionUID = -885271089152016693L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "restaurant_id", nullable = false)
	private Long restaurantId;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "payment_option", nullable = false)
	private String paymentOption;

	@Column(name = "value", nullable = false)
	private Double value;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "phone", nullable = false)
	private String phone;

	@Column(name = "street", nullable = false)
	private String street;

	@Column(name = "number", nullable = false)
	private Long number;

	@Column(name = "optional_adress")
	private String optionalAdress;

	@JsonIgnoreProperties("order")
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> orderItems = new ArrayList<>();

	@PrePersist
	private void prePersist() {
		this.value = orderItems.stream().mapToDouble(o -> (o.getQuantity() * o.getPrice())).sum();
	}

	public void addItems(OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}
}
