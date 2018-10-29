package com.dcai.sample.jpa.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "CourseDetail")
public class CourseTable {

	@Id
	@GeneratedValue
	private Long id;

	// JPA is going to add underscore
	@Column(name = "FullName", nullable = false, unique = true, updatable = true)
	private String name;
	
	@UpdateTimestamp
	private LocalDateTime lastUpdateTime;
	
	@CreationTimestamp
	private LocalDateTime createTime;

	protected CourseTable() {
	}

	public CourseTable(String name) {
		this.name = name;
	}

}
