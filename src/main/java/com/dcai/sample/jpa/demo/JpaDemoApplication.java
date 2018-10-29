package com.dcai.sample.jpa.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dcai.sample.jpa.demo.entity.Course;
import com.dcai.sample.jpa.demo.entity.CourseTable;
import com.dcai.sample.jpa.demo.entity.Review;
import com.dcai.sample.jpa.demo.entity.ReviewRating;
import com.dcai.sample.jpa.demo.repository.CourseRepository;
import com.dcai.sample.jpa.demo.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class JpaDemoApplication implements CommandLineRunner {

	@Autowired
	private CourseRepository coureRepostory;

	@Autowired
	private StudentRepository studentRepostory;

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Long courseId = 10L;
		Course course = coureRepostory.findById(courseId);
		log.debug("find by id {}: {}", courseId, course.getName());

		coureRepostory.save(new Course("Course 100"));

		coureRepostory.playWithEntityManagerRefresh();

		coureRepostory.save(new CourseTable("Course Table 100"));

		studentRepostory.saveStudentWithPassport();

		Review review1 = new Review(ReviewRating.FIVE, "best learning experience");
		Review review2 = new Review(ReviewRating.FOUR, "out of this world");
		coureRepostory.addReviewsToCourse(10L, Arrays.asList(review1, review2));

		studentRepostory.insertStudentAndCourse();
	}
}
