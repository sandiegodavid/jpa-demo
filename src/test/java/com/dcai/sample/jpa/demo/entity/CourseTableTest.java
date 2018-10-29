package com.dcai.sample.jpa.demo.entity;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dcai.sample.jpa.demo.repository.CourseRepositoryTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = {})
@RunWith(SpringRunner.class)
@Slf4j
public class CourseTableTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCourseDetail() {
		fail("Not yet implemented");
	}

}
