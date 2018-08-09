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
import com.michaeldaviddunlap.colege_management_system.entity.Student;

@Repository
public class InstructorDAOImpl implements InstructorDAO {

	// inject session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Instructor> getInstructors() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
			
		Query<Instructor> q =
				currentSession.createQuery("from Instructor order by lastName", Instructor.class);
		
		// execute query
		List<Instructor> instructors = q.getResultList();
		
		// return results
		return instructors;
	}

	@Override
	public void saveOrUpdateInstructor(Instructor theInstructor) {
		
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save the intructor
		currentSession.saveOrUpdate(theInstructor);
		
	}

	@Override
	public Instructor getInstructor(int id) {
		
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// get the instructor
		Instructor theInstructor = currentSession.get(Instructor.class, id);
		
		
		// creating a query would be ideal...but for now
		Hibernate.initialize(theInstructor.getInstructorDetail());
		
		return theInstructor;
	}

	@Override
	public void delete(int id) {
		
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// get the instructor
		Instructor theInstructor = currentSession.get(Instructor.class, id);
		
		// remove all associations of instructor to course
		// query for all courses with id of instructor
		Query<Course> q =
				currentSession.createQuery("from Course where instructor_id=:id", Course.class);
		
		q.setParameter("id", id);
		// execute query
		List<Course> courses = q.getResultList();
		
		// set all associations to null
		for (Course course: courses) {
			course.setInstructor(null);
			currentSession.update(course);
		}
		
		// delete the instructor
		currentSession.delete(theInstructor);
		
	}
	
	

}
