package com.michaeldaviddunlap.colege_management_system.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.michaeldaviddunlap.colege_management_system.entity.Course;
import com.michaeldaviddunlap.colege_management_system.entity.Instructor;
import com.michaeldaviddunlap.colege_management_system.entity.Review;

@Repository
public class CourseDAOImpl implements CourseDAO {

	// inject session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Course> getCourses() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
			
		Query<Course> q =
				currentSession.createQuery("from Course", Course.class);
		
		// execute query
		List<Course> courses = q.getResultList();
		
		// return results
		return courses;
	}

	@Override
	public void saveOrUpdateCourse(Course theCourse) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save the course
		currentSession.saveOrUpdate(theCourse);
		
	}

	@Override
	public Course getCourse(int id) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// get the course
		Course course = currentSession.get(Course.class, id);
		
		// creating a query would be ideal...but for now
		// Hibernate.initialize(course.getInstructor().getInstructorDetail());
		Hibernate.initialize(course.getReviews());
		
		return course;
	}

	@Override
	public void delete(int id) {
		
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// get the course
		Course course = currentSession.get(Course.class, id);
		
		currentSession.delete(course);
		
	}

	@Override
	public void deleteReview(int id) {
		
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// get the review
		Review review = currentSession.get(Review.class, id);
		
		currentSession.delete(review);
		
	}
	
	

}
