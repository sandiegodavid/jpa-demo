package com.dcai.sample.jpa.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dcai.sample.jpa.demo.entity.Course;

public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {
	Course findByName(String name);

	@Query("Select c from Course c where c.name like '%10%'")
	List<Course> courseWithNameContains10();
}
