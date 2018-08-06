package com.michaeldaviddunlap.colege_management_system.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import com.michaeldaviddunlap.colege_management_system.validation.CourseCode;

@Entity
@Table(name="course")
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	// can pass a comma-delimited string and CourseCode class will break into array
	@Pattern(regexp=".{3}[\\d]{3}$", message="last three characters must be numerals")
	@CourseCode(value="ARC,BAS,MIS", message="must have valid prefix(ARC, BAS, MIS)")
	@Column(name="code")
	private String code;
	
	@Column(name="title")
	private String title;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE,
						CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="instructor_id")
	private Instructor instructor;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="course_id", nullable=false)
	private List<Review> reviews;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="course_student",
			joinColumns=@JoinColumn(name="course_id"),
			inverseJoinColumns=@JoinColumn(name="student_id")
			)
	private List<Student> students;
	
	public Course() {
		
	}
	
	public Course(String code, String title) {
		this.code = code;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code.toUpperCase();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public void addReview (Review tempReview) {
		if(reviews == null) {
			reviews = new ArrayList<>();
		}
		
		reviews.add(tempReview);
	}
	
	public void addStudent (Student tempStudent) {
		if (students == null) {
			students = new ArrayList<>();
		}
		
		students.add(tempStudent);
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", code=" + code + ", title=" + title + ", instructor=" + instructor + ", reviews="
				+ reviews + "]";
	}
	
	

}
