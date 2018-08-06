<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>Add Instructor</title>
</head>
<body>
	
	<div class="w3-container">
		<h1>Colege Management System</h1>
		
		<div class="w3-card-4">
			<div class="w3-container w3-orange">
				<h2>Add Instructor</h2>
			</div>
			
			<form:form action="process" class="w3-container" modelAttribute="instructor">
			
				<!-- need to associate form with instructor id -->
				<form:hidden path="id" />
				<form:hidden path="instructorDetail.id" />
			
				<div class="w3-row-padding">
					
					<p class="w3-half">
						<form:input class="w3-input" path="firstName" type="text" name="firstName" placeholder="First Name"/>
					</p>
					<p class="w3-half">
						<form:input class="w3-input" path="lastName" type="text" name="lastName" placeholder="Last Name"/>
						<form:errors path="lastName" />
					</p>
				</div>
				
				<p class="w3-row-padding">
					<form:input class="w3-input" path="email" type="text" name="email" placeholder="Email"/>
				</p>
				
				<p class="w3-row-padding">
					<form:input class="w3-input" path="instructorDetail.hobby" type="text" name="hobby" placeholder="Hobby"/>
				</p>
				
				<p class="w3-row-padding">
					<form:input class="w3-input" path="instructorDetail.youtubeChannel" type="text" name="youtubeChannel" placeholder="Youtube Channel"/>
				</p>
				
				<p class="w3-row-padding">
					<input class="w3-btn w3-orange" type="submit"/>
				</p>
			
			
			</form:form>
		</div>
	
	</div>
	
	
</body>
</html>