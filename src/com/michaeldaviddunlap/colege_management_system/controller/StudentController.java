package com.michaeldaviddunlap.colege_management_system.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
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
@RequestMapping("/student")
public class StudentController {
	
	// inject colege management service
	@Autowired
	private ColegeManagementService colegeManagementService;
	
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
	
	@GetMapping("/list")
	public String listStudents(Model theModel) {
		
		// get students from the dao
		List<Student> theStudents = colegeManagementService.getStudents();
		
		// add the customers to the model
		theModel.addAttribute("students", theStudents); 
		
		return "list-students";
	}
	
	// controller method to show add instructor form
	@GetMapping("/add")
	public String showStudentForm(Model theModel) 
	{
		// create student object
		Student theStudent = new Student();
		
		// add student object to model
		theModel.addAttribute("student", theStudent);
		
		return "student-form";
	}
	
	// controller method to process add student
	@PostMapping("/process")
	public String processStudentForm(
		@Valid @ModelAttribute("student") Student theStudent,
		BindingResult theBindingResult
	) {
		
		System.out.println("Last name: |" + theStudent.getLastName() +"|");
		
		System.out.println("Binding result: " + theBindingResult);
		
		System.out.println("\n\n\n\n");
		
		if (theBindingResult.hasErrors()) {
			return "student-form";
		}
		
		colegeManagementService.saveOrUpdate(theStudent);
		
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
		
		Student student = new Student();
		
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// retrieve student based on primary key
			student = session.get(Student.class, id);
			
//			Query q = session.createQuery("SELECT s FROM Student s JOIN FETCH s.courses c WHERE s.id = :id");
//			q.setParameter("id", id);
//			Student student = (Student) q.getSingleResult();
			
 			Hibernate.initialize(student.getCourses());
			theModel.addAttribute("student", student);
			System.out.println("Get complete: " + student);
			// System.out.println(student.getCourses());
			
			// get Course list
			@SuppressWarnings("unchecked")
			List<Course> theCourses = session
						.createQuery("from Course")
						.getResultList();

			
			
			// commit transaction
			session.getTransaction().commit();
			List<Course> studentCourses = student.getCourses();
			System.out.println(">>>>>>>> Here's the student courses: " + studentCourses);
			
			// remove courses student is already taking
			
			List<Course> filteredCourses = theCourses.stream().filter((course) -> (alreadyTakingCourse(course,studentCourses))).collect(Collectors.toList());
			
			theModel.addAttribute("courses", filteredCourses);
			
			
			System.out.println("Done!");
			System.out.println(student);
		}
		finally {
			session.close();
			factory.close();
		}
		
		System.out.println(">>>>>>>> Here's the student: " + student);
		
		return "student-detail";
		
	}
	
	@GetMapping("/update")
	public String update(
		@RequestParam("studentId") int id,
		Model theModel
	) {

		// get customer from service
		Student student = colegeManagementService.getStudent(id);
		
		// set customer as a model attribute for pre-populating
		theModel.addAttribute("student", student);

		// send to form
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
			session.close();
			factory.close();
		}
		
		return "redirect:/student/list";
		
	}
	
	@RequestMapping("/addCourse")
	public String addCourse (@RequestParam(value="checkedCourse", required=false) List<String> courseIds,
							@RequestParam("studentId") String studentId) {
		
		if(courseIds == null) {
			return "redirect:/student/" + studentId;
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
			// get customer from db
			Student student = session.get(Student.class, Integer.parseInt(studentId));
			
			
//			Query q = session.createQuery("SELECT s FROM Student s JOIN FETCH s.courses c WHERE s.id = :id");
//			q.setParameter("id", id);
//			Student student = (Student) q.getSingleResult();
			for(String courseId:courseIds) {
				
				Query<?> qry=session.createQuery("SELECT c From Course c where c.id=:id");
				qry.setParameter("id", Integer.parseInt(courseId));
				Course course = (Course) qry.getSingleResult();
				student.addCourse(course);
			}
			
			
			// add course
			
			// commit transaction
			session.getTransaction().commit();
		} finally {
			session.close();
			factory.close();
		}
		
		
		return "redirect:/student/" + studentId;
	}
	
	public boolean alreadyTakingCourse (Course theCourse, List<Course> studentCourses) {
		for (Course studentCourse: studentCourses ) {
			if (theCourse.getId() == studentCourse.getId())
				return false;
		}
		
		return true;
	}
	
	
}
