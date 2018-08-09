package com.michaeldaviddunlap.colege_management_system.playground;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.michaeldaviddunlap.colege_management_system.entity.Course;
import com.michaeldaviddunlap.colege_management_system.entity.Instructor;
import com.michaeldaviddunlap.colege_management_system.entity.InstructorDetail;
import com.michaeldaviddunlap.colege_management_system.entity.Review;
import com.michaeldaviddunlap.colege_management_system.entity.Student;

public class CreateInstructorDemo {
	
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
			
			// create the objects
			Instructor tempInstructor =
					new Instructor ("Susan", "Public", "susan@public.com");
			
			InstructorDetail tempInstructorDetail =
					new InstructorDetail(
							"http://yeahwhatever",
							"Luv to game!!!"
					);
			
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			Course tempCourse1 = new Course ("MIS100", "SB Course A");
			Course tempCourse2 = new Course ("MIS100", "SB Course B");
			Course tempCourse3 = new Course ("MIS100", "SB Course C");
			
			tempCourse1.setInstructor(tempInstructor);
			tempCourse2.setInstructor(tempInstructor);
			tempCourse3.setInstructor(tempInstructor);
			
			session.beginTransaction();
			
			session.save(tempInstructor);
			session.save(tempCourse1);
			session.save(tempCourse2);
			session.save(tempCourse3);
			
			session.getTransaction().commit();
					
		} finally {
			session.close();
			session.close();
		}
	}
}

