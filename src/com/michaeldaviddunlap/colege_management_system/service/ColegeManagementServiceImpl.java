package com.michaeldaviddunlap.colege_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.michaeldaviddunlap.colege_management_system.dao.CourseDAO;
import com.michaeldaviddunlap.colege_management_system.dao.InstructorDAO;
import com.michaeldaviddunlap.colege_management_system.dao.StudentDAO;
import com.michaeldaviddunlap.colege_management_system.entity.Course;
import com.michaeldaviddunlap.colege_management_system.entity.Instructor;
import com.michaeldaviddunlap.colege_management_system.entity.Student;

@Service
public class ColegeManagementServiceImpl implements ColegeManagementService {

	// inject student dao
	@Autowired
	private StudentDAO studentDAO;
	
	@Autowired
	private CourseDAO courseDAO;
	
	@Autowired
	private InstructorDAO instructorDAO;
	
	@Override
	@Transactional
	public List<Student> getStudents() {
		
		return studentDAO.getStudents();
	}

	@Override
	@Transactional
	public List<Course> getCourses() {
		
		return courseDAO.getCourses();
	}

	@Override
	@Transactional
	public List<Instructor> getInstructors() {
		return instructorDAO.getInstructors();
	}

	@Override
	@Transactional
	public void saveOrUpdate(Student theStudent) {
		studentDAO.saveOrUpdateStudent(theStudent);
	}

	@Override
	@Transactional
	public void saveOrUpdate(Course theCourse) {
		courseDAO.saveOrUpdateCourse(theCourse);
		
	}

	@Override
	@Transactional
	public void saveOrUpdate(Instructor theInstructor) {
		instructorDAO.saveOrUpdateInstructor(theInstructor);
		
	}

	@Override
	@Transactional
	public Student getStudent(int id) {
		return studentDAO.getStudent(id);
	}

	@Override
	@Transactional
	public Instructor getInstructor(int id) {
		
		return instructorDAO.getInstructor(id);
	}

	@Override
	@Transactional
	public Course getCourse(int id) {
		
		return courseDAO.getCourse(id);
	}

	@Override
	@Transactional
	public void deleteCourse(int id) {
		
		courseDAO.delete(id);
		
	}

	@Override
	@Transactional
	public void deleteInstructor(int id) {
		instructorDAO.delete(id);
		
	}

	@Override
	@Transactional
	public void deleteReview(int reviewId) {
		
		courseDAO.deleteReview(reviewId);
		
	}

	@Override
	@Transactional
	public List<Student> searchStudents(String searchName) {
		
		return studentDAO.searchStudents(searchName);
	}
	
	

}
