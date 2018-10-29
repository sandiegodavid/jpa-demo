package com.dcai.sample.jpa.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Student {

	@Id
	@GeneratedValue
	private Long id;

	@NonNull
	private String name;

	@OneToOne(fetch = FetchType.LAZY)
	// @OneToOne
	private Passport passport;

	private Address address;

	@ManyToMany
	@JoinTable(name = "Student_Course",
	        joinColumns = @JoinColumn(name = "Student_Id"),
	        inverseJoinColumns = @JoinColumn(name = "Course_Id"))
	private List<Course> courses = new ArrayList<>();

	public String toString() {
		return name;
	}

	public void addCourse(Course course) {
		courses.add(course);
	}
}
