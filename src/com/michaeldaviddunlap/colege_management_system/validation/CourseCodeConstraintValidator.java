package com.michaeldaviddunlap.colege_management_system.validation;

import java.lang.annotation.Annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String>{

	private String coursePrefixes;
	
	@Override
	public void initialize(CourseCode theCourseCode) {
		
		coursePrefixes = theCourseCode.value();
	}

	@Override
	public boolean isValid(String theCode, ConstraintValidatorContext theConstraintValidatorContext) {
		
		String[] arrCoursePrefixes = coursePrefixes.split(",");
		
		for (String coursePrefix: arrCoursePrefixes) {
			if(theCode.toUpperCase().startsWith(coursePrefix)) {
				return true;
			}
		}
		
		return false;
	}
	
	
}
