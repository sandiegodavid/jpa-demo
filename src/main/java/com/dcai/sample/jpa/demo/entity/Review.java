package com.dcai.sample.jpa.demo.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class Review {

	@Id
	@GeneratedValue
	private Long id;

	@NonNull
	@Enumerated(EnumType.STRING)
	private ReviewRating rating;

	@NonNull
	private String description;

	@ManyToOne
	private Course course;
}
