package com.michaeldaviddunlap.colege_management_system.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.michaeldaviddunlap.colege_management_system.entity.Course;
import com.michaeldaviddunlap.colege_management_system.entity.Instructor;
import com.michaeldaviddunlap.colege_management_system.entity.InstructorDetail;
import com.michaeldaviddunlap.colege_management_system.entity.Review;
import com.michaeldaviddunlap.colege_management_system.entity.Student;
import com.michaeldaviddunlap.colege_management_system.service.ColegeManagementService;

@Controller
@RequestMapping("/course")
public class CourseController {
	
	// inject colege management service
	@Autowired
	private ColegeManagementService colegeManagementService;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(Course.class, stringTrimmerEditor);
	}
	
	@GetMapping("/list")
	public String listCourse(Model theModel) {
		
		// get courses from DAO
		List<Course> theCourses = colegeManagementService.getCourses();
			
		// add to model
		theModel.addAttribute("courses", theCourses);
		
		return "list-courses";
	}
	
	// controller method to show add instructor form
	@GetMapping("/add")
	public String showCourseForm(Model theModel) {
		// get list of instructors for course form
		List<Instructor> theInstructors = colegeManagementService.getInstructors();
		
		// add instructor list to model
		theModel.addAttribute("instructors", theInstructors);
		
		// System.out.println(">>>> Instructors: " + theInstructors);

		// create course object
		Course theCourse = new Course();		
		
		// add course object to model
		theModel.addAttribute("course", theCourse);

		// System.out.println("The MODEL:" + theModel);
		
		return "course-form";
	}
	
	// controller method to process add course
	@PostMapping("/process")
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
			
			// get list of instructors for return to course form
			List<Instructor> theInstructors = colegeManagementService.getInstructors();
			
			// add instructor list to model
			theModel.addAttribute("instructors", theInstructors);
			
			// System.out.println(">>>> Instructors: " + theInstructors);
			
			
			return "course-form";
		}
		
		colegeManagementService.saveOrUpdate(theCourse);
		
		return "redirect:/course/list";
	}
	
	@GetMapping("/update")
	public String update(
		@RequestParam("courseId") int id,
		Model theModel
	) {
		

		// get list of instructors for form	
		List<Instructor> theInstructors = colegeManagementService.getInstructors();
		
		// set instructor list (a check in the form will select current instructor
		theModel.addAttribute("instructors", theInstructors);
			
		Course course = colegeManagementService.getCourse(id);
		
		// set customer as a model attribute for pre-populating
		theModel.addAttribute("course", course);

		System.out.println("LOOK HERE>> " + course);
			

		
		return "course-form";
		
	}
	@GetMapping("/delete")
	public String deleteCourse(
		@RequestParam("courseId") int id
	) {
		

//		Course course = session.get(Course.class, id);
//		
//		// check to make sure there is actually a record to delete
//		if (course != null) {
//			course.setInstructor(null);
//			
//			// delete customer
//			session.delete(course);
//		}
		
		colegeManagementService.deleteCourse(id);

		
		return "redirect:/course/list";
		
	}
	
	@GetMapping("/reviews")
	public String reviews(
		Model theModel,
		@RequestParam("courseId") int id
	) {

		// get reviews from DAO
		Course course = colegeManagementService.getCourse(id);
		
		// add course to the model
		theModel.addAttribute("course", course);

		return "course-reviews";
		
	}
	
	// controller method to show form for adding a review
	@GetMapping("/reviews/add")
	public String showReviewForm(Model theModel, @RequestParam("courseId") int id) {

		// get the course
		Course course = colegeManagementService.getCourse(id);
		
		// add course to model
		theModel.addAttribute("course", course);
	
		// create reveiw object
		Review theReview = new Review();
	
		// add review object to model
		theModel.addAttribute("review", theReview);
	
	return "review-form";
	
	}
	
	// controller method to process add review
	@PostMapping("/reviews/process")
	public String processReviewForm(
		@RequestParam("courseId") int courseId,
		@Valid @ModelAttribute("review") Review theReview,
		BindingResult theBindingResult,
		Model theModel
	) 
	{
		if (theBindingResult.hasErrors()) {

				
			// get the course
			Course course = colegeManagementService.getCourse(courseId);
				
			// add to model for return to view
			theModel.addAttribute("course", course);
			
			return "review-form";
		}

			
		// get the course
		Course course = colegeManagementService.getCourse(courseId);
		
		// add the review to the review list for course
		course.addReview(theReview);
		colegeManagementService.saveOrUpdate(course);
		
		
		return "redirect:/course/reviews?courseId=" + courseId;
	}
	
	@GetMapping("/reviews/deleteReview")
	public String deleteCourseReview(
		@RequestParam("reviewId") int reviewId,
		@RequestParam("courseId") int courseId
	) {
		
		colegeManagementService.deleteReview(reviewId);
		
		return "redirect:/course/reviews?courseId=" + courseId;
		
	}
}
