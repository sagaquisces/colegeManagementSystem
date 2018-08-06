package com.michaeldaviddunlap.colege_management_system.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.michaeldaviddunlap.colege_management_system.entity.Instructor;
import com.michaeldaviddunlap.colege_management_system.entity.InstructorDetail;

public class StudentInstructorBind {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			Instructor instructor =
				new Instructor();
			
			InstructorDetail instructorDetail =
				new InstructorDetail();
			
			instructor.setInstructorDetail(instructorDetail);
			
			session.beginTransaction();
			
			session.save(instructor);
			
			session.getTransaction().commit();
			
			
		} finally {
			
			factory.close();
		}

	}

}
