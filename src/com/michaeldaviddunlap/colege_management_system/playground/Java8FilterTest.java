package com.michaeldaviddunlap.colege_management_system.playground;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.michaeldaviddunlap.colege_management_system.entity.Course;
import com.michaeldaviddunlap.colege_management_system.entity.Instructor;
import com.michaeldaviddunlap.colege_management_system.entity.InstructorDetail;
import com.michaeldaviddunlap.colege_management_system.entity.Review;
import com.michaeldaviddunlap.colege_management_system.entity.Student;

public class Java8FilterTest {
	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		Student student = new Student();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// retrieve student based on primary key
			student = session.get(Student.class, 2);
			Hibernate.initialize(student.getCourses());
			
			// commit transaction
			session.getTransaction().commit();
			
			session.beginTransaction();
			List<Course> courses = student.getCourses();
			session.getTransaction().commit();
			
//			System.out.println("Get complete: " + student);
//			System.out.println("Courses: " + courses);
//			System.out.println("Done!");
		}
		finally {
			session.close();
			factory.close();
		}
		
		System.out.println(student);
		

//		List<Student> students = Arrays.asList(
//				new Student("Falcon", "Crest", "falcon@crest.com"),
//				new Student("Gery", "Trool", "gery@trool.com"),
//				new Student("Sno", "Bleep", "sno@bleep.com"),
//				new Student("Bo", "Bleep", "sno@bleep.com")
//		);
//				
//		List<Student> result = students.stream()
//								.filter(student -> "Bleep".equals(student.getLastName()))
//								.collect(Collectors.toList());
//		
//		result.forEach(System.out::println);
	}
}
