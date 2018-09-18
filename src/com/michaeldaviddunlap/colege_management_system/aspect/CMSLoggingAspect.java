package com.michaeldaviddunlap.colege_management_system.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CMSLoggingAspect {
	
	// setup logger
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	// setup pointcut declarations
	@Pointcut("execution(* com.michaeldaviddunlap.colege_management_system.controller.*.*(..))")
	private void forControllerPackage () {}
	
	@Pointcut("execution(* com.michaeldaviddunlap.colege_management_system.dao.*.*(..))")
	private void forDAOPackage () {}
	
	@Pointcut("execution(* com.michaeldaviddunlap.colege_management_system.entity.*.*(..))")
	private void forEntityPackage () {}
	
	@Pointcut("forControllerPackage() || forDAOPackage() || forEntityPackage()")
	private void forAppFlow() {}
	
	// add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {
		
		// display method we are calling
		String method = joinPoint.getSignature().toShortString();
		myLogger.info("======> in @Before: calling method; " + method);
		
		// get the arguments
		Object[] args = joinPoint.getArgs();		
		// loop thru and display
		for (Object arg: args) {
			myLogger.info("======> argument: " + arg);
		}
	}
	
	
	// add @AfterReturning advice
	@AfterReturning(
		pointcut="forAppFlow()",
		returning="result"
	)
	public void afterReturning(JoinPoint joinPoint, Object result) {
		// display method we are returning from
		String method = joinPoint.getSignature().toShortString();
		myLogger.info("======> in @AfterReturning: calling method; " + method);
		
		// display data
		myLogger.info("======> result: " + result);		
		
	}
	

}
