package com.dcai.sample.jpa.demo.repository;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.dcai.sample.jpa.demo.entity.Course;
import com.dcai.sample.jpa.demo.entity.Review;
import com.dcai.sample.jpa.demo.entity.Student;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = {})
@RunWith(SpringRunner.class)
@Slf4j
public class CourseRepositoryTest {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private EntityManager em;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	@DirtiesContext
	public void testplayWithEntityManagerDetachClear() {
		log.debug("playWithEntityManager - start");
		courseRepository.playWithEntityManagerDetachClear();
	}

	@Test
	@Transactional
	public void retrieveReviewsForCourse() {
		Course course = courseRepository.findById(10L);
		log.info("reviews for course {}: {}", 10L, course.getReviews());
	}

	@Test
	@Transactional
	public void retrieveCourseForReview() {
		Long rid = 20000L;
		Review review = em.find(Review.class, rid);
		log.info("course for review {}: {}", rid, review.getCourse());
	}

	@Test
	@Transactional // without @Transactional, no FLC
	public void testFirstLevelCache() {
		Course course = courseRepository.findById(10L);
		log.info("Course: {}", course);

		Course course1 = courseRepository.findById(10L);
		log.info("Course again: {}", course1);
	}

	@Test
	public void testSoftDelete() {
		courseRepository.deleteById(28L);
		Course course = courseRepository.findById(28L);
		log.info("Course can't be found any more: {}", course == null);
	}

	@Test
	@Transactional
	@DirtiesContext
	public void testPerformance() {
		EntityGraph<Course> graph = em.createEntityGraph(Course.class);
		Subgraph<List<Student>> bookSubGraph = graph.addSubgraph("students");

		List<Course> courses = em.createQuery("Select c from Course c", Course.class)
		        .setHint("javax.persistence.loadgraph", graph)
		        .getResultList();
		for (Course course : courses) {
			log.info("{} has students: {}", course, course.getStudents());
		}
	}

}
