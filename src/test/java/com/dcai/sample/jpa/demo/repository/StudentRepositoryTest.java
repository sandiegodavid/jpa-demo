package com.dcai.sample.jpa.demo.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dcai.sample.jpa.demo.entity.Address;
import com.dcai.sample.jpa.demo.entity.Passport;
import com.dcai.sample.jpa.demo.entity.Student;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = {})
@RunWith(SpringRunner.class)
@Slf4j
public class StudentRepositoryTest {

	@Autowired
	private EntityManager em;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	@Transactional
	public void retrieveStudentAndPassportDetails() {
		Student student = em.find(Student.class, 3L);
		log.debug("student: {}", student);
		log.debug("passport: {}", student.getPassport());
	}

	@Test
	@Transactional // Persistence Context
	public void testTransaction() {
		Student student = em.find(Student.class, 3L);
		// Persistence Context(student)

		Passport passport = student.getPassport();
		// Persistence Context(student, passport)

		passport.setNumber("5678");
		// Persistence Context(student, passport++)

		student.setName("emily sheep");
		// Persistence Context(student++, passport)
	}

	@Test
	@Transactional
	public void retrieveStudentAndCourses() {
		Student student = em.find(Student.class, 1L);
		log.debug("student {} has courses: {}", student, student.getCourses());

	}

	@Test
	@Transactional
	public void testEmbedable() {
		Student student = em.find(Student.class, 3L);
		Address address = new Address();
		address.setStreet("disneyland");
		student.setAddress(address);
		em.flush();
		log.debug("address added.");
	}

}
