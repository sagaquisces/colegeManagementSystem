<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>Add Student</title>
</head>
<body>
	
	<div class="w3-container">
		<h1>Colege Management System</h1>
		
		<div class="w3-card-4">
			<div class="w3-container w3-orange">
				<h2>Add Student</h2>
			</div>
			
			<form:form action="process" class="w3-container" modelAttribute="student">
				
				<!-- need to associate form with customer id -->
				<form:hidden path="id" />
				
				<p>
					<form:input class="w3-input" path="firstName" type="text" name="firstName" placeholder="First Name"/>
				</p>
				<p>
					<form:input class="w3-input" path="lastName" type="text" name="lastName" placeholder="Last Name (required)"/>
					<form:errors path="lastName" />
				</p>
				
				<p>
					<form:input class="w3-input" path="email" type="text" name="email" placeholder="Email"/>
				</p>
				
				<p>
					<form:input class="w3-input" path="dateOfBirth" type="text" name="dateOfBirth" placeholder="Date of Birth mm/dd/yyyy"/>
					<form:errors path="dateOfBirth" />
				</p>
				<p>
					<input class="w3-btn w3-orange" type="submit"/>
				</p>
			
			</form:form>
		</div>
	
	</div>
	
	
</body>
</html>