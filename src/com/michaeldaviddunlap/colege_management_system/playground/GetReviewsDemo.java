package com.michaeldaviddunlap.colege_management_system.playground;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.michaeldaviddunlap.colege_management_system.entity.Course;
import com.michaeldaviddunlap.colege_management_system.entity.Instructor;
import com.michaeldaviddunlap.colege_management_system.entity.InstructorDetail;
import com.michaeldaviddunlap.colege_management_system.entity.Review;

public class GetReviewsDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// create a course
			Course tempCourse = new Course("ARC221", "Sock It To Me Training");
			
			// add some reviews
			tempCourse.addReview(new Review("This course sucked."));
			tempCourse.addReview(new Review("This course was great."));
			tempCourse.addReview(new Review("I want my money back."));
			
			// save the course and leverage cascade
			session.save(tempCourse);
			
			// commit transaction
			session.getTransaction().commit();
			
		}
		finally {
			
			// add clean up code
			session.close();
			
			factory.close();
		}

	}

}
