package com.michaeldaviddunlap.colege_management_system.dao;

import java.util.List;

import com.michaeldaviddunlap.colege_management_system.entity.Instructor;

public interface InstructorDAO {
	
	public List<Instructor> getInstructors();

	public void saveOrUpdateInstructor(Instructor theInstructor);

	public Instructor getInstructor(int id);

	public void delete(int id);
}
