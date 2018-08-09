package com.michaeldaviddunlap.colege_management_system.service;

import java.util.List;

import com.michaeldaviddunlap.colege_management_system.entity.Course;
import com.michaeldaviddunlap.colege_management_system.entity.Instructor;
import com.michaeldaviddunlap.colege_management_system.entity.Student;

public interface ColegeManagementService {
	
	public List<Student> getStudents();
	
	public List<Course> getCourses();
	
	public List<Instructor> getInstructors();

	public void saveOrUpdate(Student theStudent);
	
	public void saveOrUpdate(Course theCourse);
	
	public void saveOrUpdate(Instructor theInstructor);

	public Student getStudent(int id);

	public Instructor getInstructor(int id);
	
	public Course getCourse(int id);

	public void deleteCourse(int id);

	public void deleteInstructor(int id);

	public void deleteReview(int reviewId);

	public List<Student> searchStudents(String searchName);

}
