package com.michaeldaviddunlap.colege_management_system.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;

// import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.michaeldaviddunlap.colege_management_system.entity.Course;
import com.michaeldaviddunlap.colege_management_system.entity.Instructor;
import com.michaeldaviddunlap.colege_management_system.entity.InstructorDetail;
import com.michaeldaviddunlap.colege_management_system.entity.Review;
import com.michaeldaviddunlap.colege_management_system.entity.Student;
import com.michaeldaviddunlap.colege_management_system.utils.DateUtils;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	// add an initbinder to convert input strings
	// remove leading and trailing whitespace
	// resolve issue for our validaion
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
//	@Value("#{countryOptions}")
//	private Map<String, String> countryOptions;
	
	@RequestMapping("/list")
	public String listStudents(Model theModel) {
		
		/////////////
		//	start db operations
		////////////
		
		// create session factory
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
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			
			System.out.println("Getting student list...");
			List<Student> theStudents =
					session
						.createQuery("from Student")
						.getResultList();
			
			// commit transaction
			session.getTransaction().commit();
			theModel.addAttribute("students", theStudents);
			
			System.out.println(">>>> Students: " + theStudents);
		}
		finally {
			factory.close();
		}
		
		
		return "list-students";
	}
	
	// controller method to show add instructor form
	@RequestMapping("/add")
	public String showStudentForm(Model theModel) 
	{
		// create student object
		Student theStudent = new Student();
		
		// add student object to model
		theModel.addAttribute("student", theStudent);
		
		// add the country options
		// theModel.addAttribute("theCountryOptions", countryOptions);
		
		return "student-form";
	}
	
	// controller method to process add student
	@RequestMapping("/process")
	// public String processInstructorForm(HttpServletRequest request, Model model) {
	public String processStudentForm(
//		@RequestParam("firstName") String theFirstName,
//		@RequestParam("lastName") String theLastName,
//		Model model
		@Valid @ModelAttribute("student") Student theStudent,
		BindingResult theBindingResult
	) {
		// read the request parameter from the HTML form
		// String theFirstName = request.getParameter("firstName");
		// String theLastName = request.getParameter("lastName");
		
		// convert the data to all caps
		// theFirstName = theFirstName.toUpperCase();
		// theLastName = theLastName.toUpperCase();
		
		// create message
		// String result = "You have added a student: " + theFirstName + " " + theLastName + ".";
		
		// add message to model
		// model.addAttribute("message", result);
		
		System.out.println("Last name: |" + theStudent.getLastName() +"|");
		
		System.out.println("Binding result: " + theBindingResult);
		
		System.out.println("\n\n\n\n");
		
		if (theBindingResult.hasErrors()) {
			return "student-form";
		}
		
		/////////////
		//	start db operations
		////////////
		
		// create session factory
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
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("Saving the student...");
			session.saveOrUpdate(theStudent); // one of the coolest factory methods; will sniff for id and save or update based on null or id.
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Saving student. Generated id:" + theStudent.getId());
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
		
		//log the input data
		System.out.println("theStudent: " + theStudent.getFirstName() + " " + theStudent.getLastName());
		
		return "redirect:/student/list";
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public String getStudentById(@PathVariable int id, Model theModel) {
		
		/////////////
		//	start db operations
		////////////
		
		// create session factory
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
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// retrieve student based on primary key
			Student student = session.get(Student.class, id);
			theModel.addAttribute("student", student);
			System.out.println("Get complete: " + student);
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
		
		return "student-detail";
		
	}
	
	@GetMapping("/update")
	public String update(
		@RequestParam("studentId") int id,
		Model theModel
	) {
		
		/////////////
		//	start db operations
		////////////
		
		// create session factory
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
		
		try {
			
			// start a transaction
			session.beginTransaction();
			// get customer from db
			Student student = session.get(Student.class, id);
			
			// set customer as a model attribute for pre-populating
			theModel.addAttribute("student", student);
			
			
			// send to form
			// commit transaction
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
		
		return "student-form";
		
	}
	
	@GetMapping("/delete")
	public String update(
		@RequestParam("studentId") int id
	) {
		
		/////////////
		//	start db operations
		////////////
		
		// create session factory
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
		
		try {
			
			// start a transaction
			session.beginTransaction();
			// get customer from db
			Student student = session.get(Student.class, id);
			
			// delete customer
			session.delete(student);
			
			// commit transaction
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
		
		return "redirect:/student/list";
		
	}
	
	
}
