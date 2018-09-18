package com.michaeldaviddunlap.colege_management_system.playground;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.michaeldaviddunlap.colege_management_system.playground.dao.AccountDAO;
import com.michaeldaviddunlap.colege_management_system.playground.dao.MembershipDAO;
import com.michaeldaviddunlap.colege_management_system.playground.entity.Account;

public class AOPDemoMain {
	

	public static void main(String[] args) {
		
		// read spring config java
		AnnotationConfigApplicationContext context = 
			new AnnotationConfigApplicationContext(AOPDemoConfig.class);
		
		// get account dao bean from container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		// call method to find the accounts
		List<Account> theAccounts = theAccountDAO.findAccounts();
		
		// display the accounts
		System.out.println("\n\nMain Program: AOPDemoMain app");
		
		System.out.println(theAccounts);
		
//		// get membership dao bean from container
//		MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
//		
//		// call business method
//		Account myAccount = new Account();
//		theAccountDAO.addAccount(myAccount);
//		theMembershipDAO.addAccount();
//		
//		// call the accountdao getters and setters
//		theAccountDAO.setName("foobar");
//		theAccountDAO.setServiceCode("silver");
//		
//		String name = theAccountDAO.getName();
//		String code = theAccountDAO.getServiceCode();
//		
//		// call business method
//		theAccountDAO.updateAccount();
//		theMembershipDAO.updateAccount();
		
		// close the context
		context.close();
	}
}
