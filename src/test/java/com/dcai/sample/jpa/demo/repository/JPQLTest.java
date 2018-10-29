package com.dcai.sample.jpa.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dcai.sample.jpa.demo.entity.Course;
import com.dcai.sample.jpa.demo.entity.Student;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = {})
@RunWith(SpringRunner.class)
@Slf4j
public class JPQLTest {

	@Autowired
	private EntityManager em;

	@Test
	@Transactional
	public void jPQLUpdate() {
		Query query = em.createNativeQuery("update course_detail set last_update_time = sysdate()");
		int noOfRowsUpdated = query.executeUpdate();
		log.debug("noOfRowsUpdated: {}", noOfRowsUpdated);
	}

	@Test
	public void jPQL() {
		TypedQuery<Course> myQuery = em.createNamedQuery("get_course_above_10", Course.class);
		List<Course> resultList = myQuery.getResultList();
		resultList.forEach(r -> {
			log.debug("course: {}", r);
		});
		log.debug("the whole list as well: {}", resultList);
	}

	@Test
	public void jPQLNative() {
		Query query = em.createNativeQuery("select * from course where id = ?", Course.class);
		query.setParameter(1, 10L);
		List<Course> resultList = query.getResultList();
		log.debug("testJPQLNative. the whole list: {}", resultList);
	}

	@Test
	public void jPQLNamedNative() {
		Query query = em.createNativeQuery("select * from course where id = :id", Course.class);
		query.setParameter("id", 11L);
		List<Course> resultList = query.getResultList();
		log.debug("testJPQLNamedNative. the whole list: {}", resultList);
	}

	@Test
	public void jPQL_get_course_without_students() {
		// NOTE: in JPQL, Entity/Field names are case sensitive and must match class
		TypedQuery<Course> myQuery
		        = em.createQuery("Select c from Course c where c.students is empty", Course.class);
		List<Course> resultList = myQuery.getResultList();
		log.debug("course without students: {}", resultList);
	}

	@Test
	public void jPQL_get_course_with_atleast_2_students() {
		TypedQuery<Course> myQuery
		        = em.createQuery("Select c from Course c where size(c.students) >= 2", Course.class);
		List<Course> resultList = myQuery.getResultList();
		log.debug("course with at least students: {}", resultList);
	}

	@Test
	@Transactional
	public void jPQL_get_course_orderby_no_students() {
		TypedQuery<Course> myQuery
		        = em.createQuery("Select c from Course c order by size(c.students) desc", Course.class);
		List<Course> resultList = myQuery.getResultList();
		StringBuilder sb = new StringBuilder();
		for (Course c : resultList) {
			sb.append("\ncourse: " + c + ", no. students: " + c.getStudents().size());
		}
		log.debug(sb.toString());
	}

	@Test
	public void jPQL_get_course_with_certain_pattern() {
		TypedQuery<Course> myQuery
		        = em.createQuery("Select c from Course c where c.name like '%10%'", Course.class);
		List<Course> resultList = myQuery.getResultList();
		log.debug("course 10 in name: {}", resultList);
	}

	@Test
	public void jPQL_join() {
		Query myQuery
		        = em.createQuery("Select c, s from Course c JOIN c.students s");
		List<Object[]> resultList = myQuery.getResultList();
		for (Object[] result : resultList) {
			log.debug("join result: course: {}, student: {}", result[0], result[1]);
		}
	}

	@Test
	public void jPQL_left_join() {
		Query myQuery
		        = em.createQuery("Select c, s from Course c LEFT JOIN c.students s");
		List<Object[]> resultList = myQuery.getResultList();
		for (Object[] result : resultList) {
			log.debug("join result: course: {}, student: {}", result[0], result[1]);
		}
	}

}
