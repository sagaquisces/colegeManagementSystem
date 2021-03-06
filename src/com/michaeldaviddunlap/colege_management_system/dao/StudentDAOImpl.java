package com.michaeldaviddunlap.colege_management_system.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.michaeldaviddunlap.colege_management_system.entity.Student;

@Repository
public class StudentDAOImpl implements StudentDAO {

	// inject session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Student> getStudents() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
			
		Query<Student> q =
				currentSession.createQuery("from Student order by lastName", Student.class);
		
		// execute query
		List<Student> students = q.getResultList();
		
		// return results
		return students;
	}

	@Override
	public void saveOrUpdateStudent(Student theStudent) {
		
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save the customer
		currentSession.saveOrUpdate(theStudent);
		
	}

	@Override
	public Student getStudent(int id) {
		
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// retrieve/read from db using id
		Student theStudent = currentSession.get(Student.class, id);
		
		return theStudent;
	}

	@Override
	public List<Student> searchStudents(String searchName) {
		
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQuery = null;
		
		//
        // only search by name if theSearchName is not empty
        //
		
		if(searchName != null && searchName.trim().length() > 0) {
			theQuery = currentSession.createQuery("from Student where lower(firstName) like :theName or lower(lastName) like :theName", Student.class);
			theQuery.setParameter("theName", "%" + searchName.toLowerCase() + "%");
		} else {
			// theSearchName is empty ... so just get all students
            theQuery =currentSession.createQuery("from Student", Student.class); 
		}
		
		// execute query and get result list
        List<Student> students = theQuery.getResultList();
		
		return students;
	}

}
