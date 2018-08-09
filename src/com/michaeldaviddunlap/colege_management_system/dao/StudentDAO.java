package com.michaeldaviddunlap.colege_management_system.dao;

import java.util.List;

import com.michaeldaviddunlap.colege_management_system.entity.Student;

public interface StudentDAO {
	
	public List<Student> getStudents();

	public void saveOrUpdateStudent(Student theStudent);

	public Student getStudent(int id);

	public List<Student> searchStudents(String searchName);
	
}
