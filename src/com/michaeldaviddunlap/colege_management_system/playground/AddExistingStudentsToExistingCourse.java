package com.michaeldaviddunlap.colege_management_system.playground;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.michaeldaviddunlap.colege_management_system.entity.Course;
import com.michaeldaviddunlap.colege_management_system.entity.Instructor;
import com.michaeldaviddunlap.colege_management_system.entity.InstructorDetail;
import com.michaeldaviddunlap.colege_management_system.entity.Review;
import com.michaeldaviddunlap.colege_management_system.entity.Student;

public class AddExistingStudentsToExistingCourse {

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
			
			// grab a course
			Course tempCourse = session.get(Course.class, 14);
			
			// grab two students
			Student tempStudent1 = session.get(Student.class, 2);
			Student tempStudent2 = session.get(Student.class, 6);
	
			
			// add students to the course
			// tempCourse.addStudent(tempStudent1);
			// tempCourse.addStudent(tempStudent2);
			
			// OR add course to students
			tempStudent1.addCourse(tempCourse);
			tempStudent2.addCourse(tempCourse); // can do either method
			
			// save the students
			System.out.println("\nSaving students ...");
			session.save(tempCourse);
			System.out.println("Saved students: " + tempCourse.getStudents());
			System.out.println("Saved courses for student1: " + tempStudent1.getCourses());
			System.out.println("Saved courses for student2: " + tempStudent2.getCourses());
			
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
