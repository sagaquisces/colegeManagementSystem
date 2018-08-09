package com.michaeldaviddunlap.colege_management_system.dao;

import java.util.List;

import com.michaeldaviddunlap.colege_management_system.entity.Course;

public interface CourseDAO {
	
	public List<Course> getCourses();

	public void saveOrUpdateCourse(Course theCourse);

	public Course getCourse(int id);

	public void delete(int id);

	public void deleteReview(int reviewId);
}
