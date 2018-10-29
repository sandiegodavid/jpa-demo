package com.dcai.sample.jpa.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Entity
@Data
@NamedQueries(
        value = {
                @NamedQuery(name = "get_course_above_10", query = "Select c from Course c where id > 10")
        })
@Cacheable
@SQLDelete(sql = "update course set is_deleted=true where id=?")
@Where(clause = "is_deleted=false")
@Slf4j
public class Course {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
	private List<Review> reviews = new ArrayList<>();

	@ManyToMany(mappedBy = "courses")
	private List<Student> students = new ArrayList<>();

	private boolean isDeleted;

	@PreRemove
	private void preRemove() {
		log.info("set isDeleted to true");
		this.isDeleted = true;
	}

	protected Course() {
	}

	public Course(String name) {
		this.name = name;
	}

	public void addReview(Review review) {
		reviews.add(review);
	}

	public String toString() {
		return name;
	}

	public void addStudent(Student student) {
		students.add(student);
	}

}
