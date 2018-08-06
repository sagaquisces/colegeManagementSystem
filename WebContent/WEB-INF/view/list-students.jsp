<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<title>List Students</title>
</head>

<body>
	<div class="w3-container">
		<h1>Colege Management System</h1>
		<hr>
		<h2>All Students</h2>
		
		<div class="w3-responsive">
			<table class="w3-table-all">
				<tr class="w3-orange">
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>DOB</th>
					<th>Actions</th>
				</tr>
				
				<!-- loop over and print students -->
				<c:forEach items="${students}" var="student">
				
					<!-- construct an "update" link with customer id -->
					<c:url var="updateLink" value="/student/update" >
						<c:param name="studentId" value="${student.id }" />
					</c:url>
					<c:url var="deleteLink" value="/student/delete" >
						<c:param name="studentId" value="${student.id }" />
					</c:url>
					<tr>
					    <td>${student.firstName}</td>
					    <td>${student.lastName}</td>
					    <td>${student.email}</td>
					    <td>${student.dateOfBirth}</td>
					    <!-- display update link -->
					    <td><a href="${updateLink}">Update</a>
					    	&nbsp;|&nbsp;
					    	<a href="${deleteLink}" onclick="return confirm('Are you sure you want to delete ${student.firstName}');">Delete</a>
					    </td>
				    </tr>
				</c:forEach>
			</table>
		</div>
		
		<p><a href="add">Add student</a></p>
	
	</div>
	
</body>

</html>