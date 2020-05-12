package com.meet.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "restaurant")
public class Restaurant implements Serializable {

	private static final long serialVersionUID = -5757717265496729229L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "phone", nullable = false)
	private String phone;

	@Column(name = "category", nullable = false)
	private String category;

	@Column(name = "delivery_estimate", nullable = false)
	private String deliveryEstimate;

	@Column(name = "about", nullable = false)
	private String about;

	@Column(name = "hours", nullable = false)
	private String hours;

	@Column(name = "street", nullable = false)
	private String street;

	@Column(name = "district", nullable = false)
	private String district;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "state", nullable = false)
	private String state;

	@Column(name = "country", nullable = false)
	private String country;

	@Column(name = "delivery_cost")
	private Double deliveryCost;

	@Column(name = "image_path")
	private String imagePath;
}
