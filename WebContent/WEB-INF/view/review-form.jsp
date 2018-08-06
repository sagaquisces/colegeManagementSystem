<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>Add Review</title>
</head>
<body>
	
	<div class="w3-container">
		<h1>Colege Management System</h1>
		
		<div class="w3-card-4">
			<div class="w3-container w3-orange">
				<h2>Add Review</h2>
				<h3>${course.title} as taught by ${course.instructor.firstName}</h3>
			</div>
			
			<form:form action="process" class="w3-container" modelAttribute="review">
			
				<!-- for update, otherwise 0 -->
				<form:hidden path="id" />
				
				<!--  course id in hidden field -->
				<input id="courseId" name="courseId" type="hidden" value="${course.id}">
			
				<p>
				<form:input class="w3-input" path="comment" type="text" name="comment" placeholder="Leave a comment..."/>
				<form:errors path="comment" />
				</p>
				
				
				<p>
				<input class="w3-btn w3-orange" type="submit"/>
				</p>

			
			</form:form>
		</div>
	
	</div>
	
	
</body>
</html>