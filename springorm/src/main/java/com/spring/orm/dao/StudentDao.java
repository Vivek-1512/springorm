package com.spring.orm.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.spring.orm.entity.Student;

public class StudentDao {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void insert(Student student) {
		// insert
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(student);
		System.out.println("Student Saved!");
	}

	@Transactional(readOnly = true)
	public Student getStudentById(int studentId) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(Student.class, studentId);
	}

	@Transactional(readOnly = true)
	public List<Student> getAllStudents() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Student", Student.class).list();
	}

	@Transactional
	public void deleteStudentById(int studentId) {
		Session session = this.sessionFactory.getCurrentSession();
		Student student = session.get(Student.class, studentId);
		if (student != null)
			session.remove(student);
	}

	@Transactional
	public void updateStudent(Student student) {
		Session session = this.sessionFactory.getCurrentSession();
		session.merge(student);
	}

}
