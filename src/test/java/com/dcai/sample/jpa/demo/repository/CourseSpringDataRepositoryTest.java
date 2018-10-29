package com.dcai.sample.jpa.demo.repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.dcai.sample.jpa.demo.entity.Course;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = {})
@RunWith(SpringRunner.class)
@Slf4j
public class CourseSpringDataRepositoryTest {

	@Autowired
	private CourseSpringDataRepository courseSpringDataRepository;

	@Test
	public void testSort() {
		Sort sort = new Sort(Sort.Direction.ASC, "name");
		log.debug("course sorted: {}", courseSpringDataRepository.findAll(sort));
	}

	@Test
	public void testPagination() {
		PageRequest pageRequest = PageRequest.of(0, 4);
		Page<Course> firstPage = courseSpringDataRepository.findAll(pageRequest);
		log.debug("first page courses: {}", firstPage.getContent());
		pageRequest = PageRequest.of(1, 5);
		Page<Course> secondPage = courseSpringDataRepository.findAll(pageRequest);
		log.debug("second page courses: {}", secondPage.getContent());
		Pageable thirdPageble = secondPage.nextPageable();
		Page<Course> thirdPage = courseSpringDataRepository.findAll(thirdPageble);
		log.debug("third page courses: {}", thirdPage.getContent());
	}

	@Test
	public void testFindByName() {
		log.debug("course find by name: {}", courseSpringDataRepository.findByName("course 26"));
	}

	@Test
	public void testCustomQuery() {
		log.debug("course with name like 10: {}", courseSpringDataRepository.courseWithNameContains10());
	}

}
