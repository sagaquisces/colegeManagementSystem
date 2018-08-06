<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<title>Colege Management System</title>
</head>

<body>
	<div class="w3-container w3-black">
	  <h1>Colege Management System</h1>
	</div>
	<div class="w3-bar w3-orange">
	    <a href="${pageContext.request.contextPath}" class="w3-bar-item w3-button">Home</a>
	    <div class="w3-dropdown-hover">
	      <button class="w3-button">Course</button>
	      <div class="w3-dropdown-content w3-bar-block w3-card-4">
	        <a href="${pageContext.request.contextPath}//course/list" class="w3-bar-item w3-button">List</a>
	        <a href="${pageContext.request.contextPath}//course/add" class="w3-bar-item w3-button">Add</a>
	      </div>
	    </div>
	    <div class="w3-dropdown-hover">
	      <button class="w3-button">Student</button>
	      <div class="w3-dropdown-content w3-bar-block w3-card-4">
	        <a href="${pageContext.request.contextPath}//student/list" class="w3-bar-item w3-button">List</a>
	        <a href="${pageContext.request.contextPath}//student/add" class="w3-bar-item w3-button">Add</a>
	      </div>
	    </div>
	    <div class="w3-dropdown-hover">
	      <button class="w3-button">Instructor</button>
	      <div class="w3-dropdown-content w3-bar-block w3-card-4">
	        <a href="${pageContext.request.contextPath}//instructor/list" class="w3-bar-item w3-button">List</a>
	        <a href="${pageContext.request.contextPath}//instructor/add" class="w3-bar-item w3-button">Add</a>
	      </div>
	    </div>
	  </div>
	<div class="w3-container">
		
		<h2>All Courses</h2>
		
		<div class="w3-responsive">
			<table class="w3-table-all">
				<tr class="w3-orange">
					<th>Code</th>
					<th>Title</th>
					<th>Actions</th>
				</tr>
				
				<!-- loop over and print courses -->
				<c:forEach items="${courses}" var="course">
				
					<!-- construct an "update" link with course id -->
					<c:url var="updateLink" value="/course/update" >
						<c:param name="courseId" value="${course.id}" />
					</c:url>
					<c:url var="deleteLink" value="/course/delete" >
						<c:param name="courseId" value="${course.id}" />
					</c:url>
					<c:url var="reviewsLink" value="/course/reviews" >
						<c:param name="courseId" value="${course.id}" />
					</c:url>
					<tr>
					    <td><em>${course.code}</em></td>
					    <td>${course.title}</td>
		
					    <!-- display update link -->
					    <td><a href="${updateLink}">Update</a>
					    	&nbsp;|&nbsp;
					    	<a href="${deleteLink}" onclick="return confirm('Are you sure you want to delete course ${course.title}');">Delete</a>
					    	&nbsp;|&nbsp;
					    	<a href="${reviewsLink}">Reviews</a>
					    </td>
				    </tr>
				</c:forEach>
			</table>
		</div>
		
		<p><a href="add">Add course</a></p>
	
	</div>
	
</body>

</html>