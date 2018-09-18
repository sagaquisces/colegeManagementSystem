package com.michaeldaviddunlap.colege_management_system.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.michaeldaviddunlap.colege_management_system.entity.Course;
import com.michaeldaviddunlap.colege_management_system.entity.Instructor;
import com.michaeldaviddunlap.colege_management_system.entity.InstructorDetail;
import com.michaeldaviddunlap.colege_management_system.entity.Review;
import com.michaeldaviddunlap.colege_management_system.entity.Student;
import com.michaeldaviddunlap.colege_management_system.service.ColegeManagementService;

@Controller
@RequestMapping("/instructor")
public class InstructorController {
	
	// inject colege management service
	@Autowired
	private ColegeManagementService colegeManagementService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(Instructor.class, stringTrimmerEditor);
	}
	
	@GetMapping("/list")
	public String listInstructors(Model theModel) {
		
		// get instructors from the dao
		List<Instructor> theInstructors = colegeManagementService.getInstructors();
		
		// add the customers to the model
		theModel.addAttribute("instructors", theInstructors); 
		
		return "list-instructors";
	}
	
	// controller method to show add instructor form
	@GetMapping("/add")
	public String showInstructorForm(Model theModel) {
		
		// create instructor object
		Instructor theInstructor = new Instructor();
		InstructorDetail theInstructorDetail = new InstructorDetail();
		
		theInstructor.setInstructorDetail(theInstructorDetail);
		
		System.out.println(">>>>>>>>>>>>" + theInstructor);
		
		// add instructor object to model
		theModel.addAttribute("instructor", theInstructor);
		
		return "instructor-form";
	}
	
	// controller method to process add instructor
	@PostMapping("/process")
	// public String processInstructorForm(HttpServletRequest request, Model model) {
	public String processInstructorForm(@Valid @ModelAttribute("instructor") Instructor theInstructor, BindingResult theBindingResult,
											@ModelAttribute("instructorDetail") InstructorDetail theInstructorDetail) {
		
		System.out.println("Last name: |" + theInstructor.getLastName() + "| " + theInstructor.getInstructorDetail());
		
		System.out.println("Binding result: " + theBindingResult);
		
		System.out.println("\n\n\n\n");
		
		if (theBindingResult.hasErrors()) {
			return "instructor-form";
		}
		

			
		// save the student object
		System.out.println("Saving the instructor..." + theInstructor);
		colegeManagementService.saveOrUpdate(theInstructor); // one of the coolest factory methods; will sniff for id and save or update based on null or id.
			
		
		//log the input data
		System.out.println("theInstructor: " + theInstructor.getFirstName() + " " + theInstructor.getLastName());
		return "redirect:/instructor/list";
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public String getInstructorById(@PathVariable int id, Model theModel) {
		
		// get instructor from the dao
		// Instructor instructor = colegeManagementService.getInstructor(id);
		// Hibernate.initialize(instructor.getCourses()); // hoping this is the simple solution. May eventually need @Transactional
		// theModel.addAttribute("instructor", instructor);
		// System.out.println("Get complete: " + instructor);
		
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
			
			// retrieve instructor based on primary key
			Instructor instructor = session.get(Instructor.class, id);
			if (instructor != null) {
				Hibernate.initialize(instructor.getCourses()); // hoping this is the simple solution. May eventually need @Transactional
			}
			
			
			theModel.addAttribute("instructor", instructor);
			System.out.println("Get complete: " + instructor);
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			session.close();
			factory.close();
		}
		
		return "instructor-detail";
		
	}
	
	@GetMapping("/update")
	public String update(
		@RequestParam("instructorId") int id,
		Model theModel
	) {
		// get instructor from service
		Instructor instructor = colegeManagementService.getInstructor(id);
		// Hibernate.initialize(instructor.getInstructorDetail());
		
		// set customer as a model attribute for pre-populating
		theModel.addAttribute("instructor", instructor);
		
		System.out.println("LOOK HERE>> " + instructor);
			
		
		return "instructor-form";
		
	}
	
	@GetMapping("/deleteInstructor")
	public String deleteInstructor(
		@RequestParam("id") int id
	) {
		

		colegeManagementService.deleteInstructor(id);

		
		return "redirect:/instructor/list";
		
	}
	
	@GetMapping("/deleteInstructorDetail")
	public String deleteInstructorDetail(
		@RequestParam("id") int id
	) {
		
		/////////////
		//	start db operations
		////////////
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			// get customer from db
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);
			
			instructorDetail.getInstructor().setInstructorDetail(null);
			// check to make sure there is actually a record to delete
			if (instructorDetail != null) {
				// delete customer
				session.delete(instructorDetail);
			}
			
			
			
			// commit transaction
			session.getTransaction().commit();
		} finally {
			session.close();
			factory.close();
		}
		
		return "redirect:/instructor/list";
		
	}
	
}
