package com.dcai.sample.jpa.demo.repository;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dcai.sample.jpa.demo.entity.Course;
import com.dcai.sample.jpa.demo.entity.CourseTable;
import com.dcai.sample.jpa.demo.entity.Review;

import lombok.extern.slf4j.Slf4j;

@Repository
@Transactional
@Slf4j
public class CourseRepository {

	@Autowired
	private EntityManager em;

	public Course findById(Long id) {
		return em.find(Course.class, id);
	}

	public void deleteById(Long id) {
		Course course = findById(id);
		em.remove(course);
	}

	public Course save(Course course) {
		if (course.getId() == null) {
			em.persist(course);
		} else {
			em.merge(course);
		}
		return course;
	}

	public void playWithEntityManagerDetachClear() {
		Course course1 = new Course("Course 1000");
		em.persist(course1);
		Course course2 = new Course("Course 2000");
		em.persist(course2);
		em.flush();

		course1.setName("Course 10000");

		em.detach(course2);
		em.clear();

		course1.setName("Course 100000");
		course2.setName("Course 20000");
		em.flush();
	}

	public void playWithEntityManagerRefresh() {
		Course course1 = new Course("Course 1000");
		em.persist(course1);
		Course course2 = new Course("Course 2000");
		em.persist(course2);
		em.flush();

		course1.setName("Course 10000");
		course2.setName("Course 20000");

		em.refresh(course1);

		em.flush();
	}

	public CourseTable save(CourseTable courseTable) {
		if (courseTable.getId() == null) {
			em.persist(courseTable);
		} else {
			em.merge(courseTable);
		}
		return courseTable;
	}

	public void addReviewsToCourse(Long courseId, List<Review> reviews) {
		Course course = findById(courseId);
		log.info("course {} reviews: {}", course, course.getReviews());

		for (Review review : reviews) {
			course.addReview(review);
			review.setCourse(course);
			em.persist(review);
		}
	}

}
