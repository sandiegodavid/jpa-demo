package com.dcai.sample.jpa.demo.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dcai.sample.jpa.demo.entity.Course;
import com.dcai.sample.jpa.demo.entity.Passport;
import com.dcai.sample.jpa.demo.entity.Student;

import lombok.extern.slf4j.Slf4j;

@Repository
@Transactional
@Slf4j
public class StudentRepository {

	@Autowired
	private EntityManager em;

	public Student findById(Long id) {
		return em.find(Student.class, id);
	}

	public void deleteById(Long id) {
		Student student = findById(id);
		em.remove(student);
	}

	public Student save(Student student) {
		if (student.getId() == null) {
			em.persist(student);
		} else {
			em.merge(student);
		}
		return student;
	}

	public void saveStudentWithPassport() {
		Passport passport = new Passport("P5000");
		em.persist(passport);

		Student student = new Student("grandpa pig");

		student.setPassport(passport);

		em.persist(student);
	}

	public void insertStudentAndCourse() {
		Student student = new Student("Grandma pig");
		em.persist(student);
		Course course = em.find(Course.class, 11L);
		student.addCourse(course);
		course.addStudent(student);
		em.persist(student);
	}

}
