package com.michaeldaviddunlap.colege_management_system.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.michaeldaviddunlap.colege_management_system.entity.Course;
import com.michaeldaviddunlap.colege_management_system.entity.Instructor;
import com.michaeldaviddunlap.colege_management_system.entity.InstructorDetail;
import com.michaeldaviddunlap.colege_management_system.entity.Review;
import com.michaeldaviddunlap.colege_management_system.entity.Student;

@Controller
@RequestMapping("/course")
public class CourseController {
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(Course.class, stringTrimmerEditor);
	}
	
	@RequestMapping("/list")
	public String listCourse(Model theModel) {
		
		/////////////
		//	start db operations
		////////////
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			
			System.out.println("Getting course list...");
			@SuppressWarnings("unchecked")
			List<Course> theCourses =
					session
						.createQuery("from Course")
						.getResultList();
			
			// commit transaction
			session.getTransaction().commit();
			theModel.addAttribute("courses", theCourses);
			
			// System.out.println(">>>> Courses: " + theCourses);
		}
		finally {
			session.close();
			factory.close();
		}
		
		return "list-courses";
	}
	
	// controller method to show add instructor form
	@RequestMapping("/add")
	public String showCourseForm(Model theModel) {
		// get Instructor List
		
		/////////////
		//	start db operations
		////////////
		
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
			
			// start a transaction
			session.beginTransaction();
			
			
			System.out.println("Getting instructor list...");
			@SuppressWarnings("unchecked")
			List<Instructor> theInstructors =
					session
						.createQuery("from Instructor")
						.getResultList();

			
			// commit transaction
			session.getTransaction().commit();
			
			// add instructor list to model
			theModel.addAttribute("instructors", theInstructors);
			
			System.out.println(">>>> Instructors: " + theInstructors);
		}
		finally {
			session.close();
			factory.close();
		}
		
		
		// create course object
		Course theCourse = new Course();		
		
		// add course object to model
		theModel.addAttribute("course", theCourse);
		
		
		System.out.println("The MODEL:" + theModel);
		
		return "course-form";
	}
	
	// controller method to process add course
	@RequestMapping("/process")
	public String processCourseForm(
		@Valid @ModelAttribute("course") Course theCourse, 
		BindingResult theBindingResult,
		Model theModel
	) 
	{
		System.out.println("Course number |" + theCourse.getCode() + "| " + theCourse.getTitle());
		
		System.out.println("Binding result: " + theBindingResult);
		
		System.out.println("\n\n\n\n");
		
		
		if (theBindingResult.hasErrors()) {
			// get Instructor List
			
			/////////////
			//	start db operations
			////////////
			
			// create session factory
			SessionFactory factory = new Configuration()
					.configure("hibernate.cfg.xml")
					.addAnnotatedClass(Instructor.class)
					.addAnnotatedClass(InstructorDetail.class)
					.addAnnotatedClass(Student.class)
					.addAnnotatedClass(Course.class)
					.addAnnotatedClass(Review.class)
					.buildSessionFactory();
			
			// create session
			Session session = factory.getCurrentSession();
			
			try {
				
				// start a transaction
				session.beginTransaction();
				
				
				System.out.println("Getting instructor list...");
				@SuppressWarnings("unchecked")
				List<Instructor> theInstructors =
						session
							.createQuery("from Instructor")
							.getResultList();
				
				// commit transaction
				session.getTransaction().commit();
				
				// add instructor list to model
				theModel.addAttribute("instructors", theInstructors);
				
				System.out.println(">>>> Instructors: " + theInstructors);
			}
			finally {
				session.close();
				factory.close();
			}
			return "course-form";
		}
		
		/////////////
		//	start db operations
		////////////
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Student.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// lazily fetch the reviews
			
			
			
			// save the course object
			System.out.println("Saving the course..." + theCourse);
			session.saveOrUpdate(theCourse); // one of the coolest factory methods; will sniff for id and save or update based on null or id.
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Saving course. Generated id:" + theCourse.getId());
			System.out.println("Done!");
		}
		finally {
			session.close();
			factory.close();
		}
		
		// String result = "You have added a course: " + theCourse.getCode() + " --- " + theCourse.getTitle() + ".";
		
		// add message to model
		// theModel.addAttribute("message", result);
		return "redirect:/course/list";
	}
	
	@GetMapping("/update")
	public String update(
		@RequestParam("courseId") int id,
		Model theModel
	) {
		
		/////////////
		//	start db operations
		////////////
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			// get customer from db
			Course course = session.get(Course.class, id);
			
			System.out.println("Getting instructor list...");
			@SuppressWarnings("unchecked")
			List<Instructor> theInstructors =
					session
						.createQuery("from Instructor")
						.getResultList();
			
			// set customer as a model attribute for pre-populating
			theModel.addAttribute("course", course);
			
			// set instructor list (a check in the form will select current instructor
			theModel.addAttribute("instructors", theInstructors);
			
			
			System.out.println("LOOK HERE>> " + course);
			
			// send to form
			// commit transaction
			session.getTransaction().commit();
		} finally {
			session.close();
			factory.close();
		}
		
		return "course-form";
		
	}
	@GetMapping("/delete")
	public String deleteCourse(
		@RequestParam("courseId") int id
	) {
		
		/////////////
		//	start db operations
		////////////
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			// get course from db
			Course course = session.get(Course.class, id);
			
			// check to make sure there is actually a record to delete
			if (course != null) {
				course.setInstructor(null);
				
				// delete customer
				session.delete(course);
			}
			
			
			
			// commit transaction
			session.getTransaction().commit();
		} finally {
			session.close();
			factory.close();
		}
		
		return "redirect:/course/list";
		
	}
	
	@GetMapping("/reviews")
	public String reviews(
		Model theModel,
		@RequestParam("courseId") int id
	) {
		
		/////////////
		//	start db operations
		////////////
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			// get course from db
			Course course = session.get(Course.class, id);
			Hibernate.initialize(course.getReviews());
			
			
			theModel.addAttribute("course", course);
			
			
			
			// commit transaction
			session.getTransaction().commit();
		} finally {
			session.close();
			factory.close();
		}
		
		return "course-reviews";
		
	}
	
	// controller method to show form for adding a review
	@GetMapping("/reviews/add")
	public String showReviewForm(Model theModel, @RequestParam("courseId") int id) {
	/////////////
	//	start db operations
	////////////
	
	// create session factory
	SessionFactory factory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(Course.class)
			.addAnnotatedClass(Student.class)
			.addAnnotatedClass(Instructor.class)
			.addAnnotatedClass(InstructorDetail.class)
			.addAnnotatedClass(Review.class)
			.buildSessionFactory();
	
	// create session
	Session session = factory.getCurrentSession();
	
	try {
		// start a transaction
		session.beginTransaction();
		
		// get the course
		Course tempCourse = session.get(Course.class, id);
		
		// add course to model
		theModel.addAttribute("course", tempCourse);
		
		// commit transaction
		session.getTransaction().commit();
					
	}
	finally {
		session.close();
		factory.close();
	}
	
	// create reveiw object
	Review theReview = new Review();
	
	// add review object to model
	theModel.addAttribute("review", theReview);
	
	return "review-form";
	
	}
	
	// controller method to process add review
	@RequestMapping("/reviews/process")
	public String processReviewForm(
		@RequestParam("courseId") String idStr,
		@Valid @ModelAttribute("review") Review theReview,
		BindingResult theBindingResult,
		Model theModel
	) 
	{
		if (theBindingResult.hasErrors()) {
			/////////////
			//	start db operations
			////////////
			
			// create session factory
			SessionFactory factory = new Configuration()
					.configure("hibernate.cfg.xml")
					.addAnnotatedClass(Course.class)
					.addAnnotatedClass(Instructor.class)
					.addAnnotatedClass(InstructorDetail.class)
					.addAnnotatedClass(Review.class)
					.buildSessionFactory();
			
			// create session
			Session session = factory.getCurrentSession();
			
			try {
				// start transaction
				session.beginTransaction();
				
				// get course
				Course tempCourse = session.get(Course.class, Integer.parseInt(idStr));
				
				// add to model for return to view
				theModel.addAttribute("course", tempCourse);
				
				// commit transaction
				session.getTransaction().commit();
			}
			finally {
				session.close();
				factory.close();
			}
			
			return "review-form";
		}

		/////////////
		//	start db operations
		////////////
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			//add review to course
			
			//theCourse.getReviews();
			Course tempCourse = session.get(Course.class, Integer.parseInt(idStr));
			
			// save the review object
			
			tempCourse.addReview(theReview);
			session.saveOrUpdate(tempCourse);
			// session.saveOrUpdate(theReview); // one of the coolest factory methods; will sniff for id and save or update based on null or id.
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			session.close();
			factory.close();
		}
		
		return "redirect:/course/reviews?courseId=" + idStr;
	}
	
	@GetMapping("/reviews/deleteReview")
	public String deleteCourseReview(
		@RequestParam("reviewId") int reviewId,
		@RequestParam("courseId") int courseId
	) {
		
		/////////////
		//	start db operations
		////////////
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			// get review from db
			Review review = session.get(Review.class, reviewId);
			
			// check to make sure there is actually a record to delete
			if (review != null) {
				
				// delete review
				session.delete(review);
			}
			
			
			
			// commit transaction
			session.getTransaction().commit();
		} finally {
			session.close();
			factory.close();
		}
		
		return "redirect:/course/reviews?courseId=" + courseId;
		
	}
}
