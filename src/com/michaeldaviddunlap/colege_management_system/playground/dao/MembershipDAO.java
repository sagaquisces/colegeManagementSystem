package com.michaeldaviddunlap.colege_management_system.playground.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {
	public void addAccount() {
		
		System.out.println(getClass() + ": DOING add DB WORK!");
	}
	
public void updateAccount() {
		
		System.out.println(getClass() + ": DOING update DB WORK!");
	}
}
