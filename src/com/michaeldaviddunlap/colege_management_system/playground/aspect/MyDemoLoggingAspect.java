package com.michaeldaviddunlap.colege_management_system.playground.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.michaeldaviddunlap.colege_management_system.playground.entity.Account;

@Aspect
@Component
public class MyDemoLoggingAspect {
	
	@AfterReturning(
			pointcut="execution(* com.michaeldaviddunlap.colege_management_system.playground.dao.AccountDAO.findAccounts(..))",
			returning="accounts"
	)
	public void afterReturningFindAccountsAdvice(
			JoinPoint joinPoint,
			List<Account> accounts
	) {
		String method = joinPoint.getSignature().toShortString();
		System.out.println("\n-------->>>>> Executing @AfterReturning on method: " + method);
		
		System.out.println("\n-------->>>>> result is: " + accounts);
	}
	
	// let's start with a @Before
	@Pointcut("execution(* com.michaeldaviddunlap.colege_management_system.playground.dao.*.*(..))")
	private void forDaoPackage() {}
	
	// create a pointcut to match getters
	@Pointcut("execution(* com.michaeldaviddunlap.colege_management_system.playground.dao.*.get*(..))")
	private void getters() {}
	
	// create a pointcut to match setters
	@Pointcut("execution(* com.michaeldaviddunlap.colege_management_system.playground.dao.*.set*(..))")
	private void setters() {}
	
	@Pointcut("forDaoPackage() && !(getters() || setters())")
	private void forDaoPackageNoGettersSetters() {}
	
	@Before("forDaoPackageNoGettersSetters()")
	public void beforeAddAccountAdvice() {
		
		System.out.println("\n======>>> Executing @Before advice on method");
	}
	
	@Before("forDaoPackageNoGettersSetters()")
	public void performApiAnalytics() {
		
		System.out.println("\n======>>> Performing API analytics");
	}
}
