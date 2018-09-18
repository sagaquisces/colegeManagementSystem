package com.michaeldaviddunlap.colege_management_system.playground.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.michaeldaviddunlap.colege_management_system.playground.entity.Account;

@Component
public class AccountDAO {
	
	private String name;
	
	private String serviceCode;
	
	// add a new method find accounts
	
	public List<Account> findAccounts() {
		List<Account> myAccounts = new ArrayList<>();
		
		// create sample accounts
		Account temp1 = new Account("John", "Silver");
		Account temp2 = new Account("Madhu", "Platinum");
		Account temp3 = new Account("Luca", "Gold");
		
		
		// add them to accounts list
		myAccounts.add(temp1);
		myAccounts.add(temp2);
		myAccounts.add(temp3);
		
		return myAccounts;
	}
	
	public String getName() {
		System.out.println(getClass() + ": getName");
		return name;
	}

	public void setName(String name) {
		System.out.println(getClass() + ": setName");
		this.name = name;
	}

	public String getServiceCode() {
		System.out.println(getClass() + ": getServiceCode");
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		System.out.println(getClass() + ": setServiceCode");
		this.serviceCode = serviceCode;
	}

	public void addAccount(Account account) {
		
		System.out.println(getClass() + ": DOING add DB WORK!");
	}
	
	public void updateAccount() {
		
		System.out.println(getClass() + ": DOING update DB WORK!");
	}
}
