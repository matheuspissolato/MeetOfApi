package com.meet.api.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "review")
public class Review implements Serializable {

	private static final long serialVersionUID = 3564144511287185553L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "restaurant_id", nullable = false)
	private Long restaurantId;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "rating", nullable = false)
	private Double rating;

	@Column(name = "comments", nullable = false)
	private String comments;

	@CreationTimestamp
	@Column(name = "created", nullable = false)
	private LocalDateTime created;

	@PrePersist
	public void prePersist() {
		this.created = LocalDateTime.now();
	}
}
