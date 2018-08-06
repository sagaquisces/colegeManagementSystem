package com.michaeldaviddunlap.colege_management_system.playground;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.michaeldaviddunlap.colege_management_system.entity.Course;
import com.michaeldaviddunlap.colege_management_system.entity.Instructor;
import com.michaeldaviddunlap.colege_management_system.entity.InstructorDetail;
import com.michaeldaviddunlap.colege_management_system.entity.Review;
import com.michaeldaviddunlap.colege_management_system.entity.Student;

public class CreateCourseAndStudents {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// create a course
			Course tempCourse = new Course("ARC202", "Pacman - How to win at it");
			
			// save the course
			System.out.println("\nSaving the course...");
			session.save(tempCourse);
			System.out.println("Saved the course: " + tempCourse);
			
			// create the students
			Student tempStudent1 = new Student("John", "Doe", "john@doe.com");
			Student tempStudent2 = new Student("Mary", "Public", "mary@publci.com");
			
			// add students to the course
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
			
			// save the students
			System.out.println("\nSaving students ...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("Saved students: " + tempCourse.getStudents());
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			session.close();
			factory.close();
		}

	}

}
