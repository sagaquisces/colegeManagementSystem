package com.michaeldaviddunlap.colege_management_system.playground;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.michaeldaviddunlap.colege_management_system.entity.Course;
import com.michaeldaviddunlap.colege_management_system.entity.Instructor;
import com.michaeldaviddunlap.colege_management_system.entity.InstructorDetail;
import com.michaeldaviddunlap.colege_management_system.entity.Review;
import com.michaeldaviddunlap.colege_management_system.entity.Student;

public class DeleteInstructorDemo {
	
	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Student.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// set id for instructor to delete
			int tempId = 29;

			session.beginTransaction();
			
			// get instructor
			Instructor tempInstructor = session.get(Instructor.class, tempId);
			
			// query for all courses with id of instructor
			Query<Course> q =
					session.createQuery("from Course where instructor_id=:id", Course.class);
			
			q.setParameter("id", tempId);
			// execute query
			List<Course> courses = q.getResultList();
			
			//
			for (Course course: courses) {
				course.setInstructor(null);
				session.update(course);
			}
			
			session.delete(tempInstructor);
			
			session.getTransaction().commit();
					
		} finally {
			session.close();
			session.close();
		}
	}
}

