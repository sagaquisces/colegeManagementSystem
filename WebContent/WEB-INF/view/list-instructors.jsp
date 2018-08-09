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
               <a href="${pageContext.request.contextPath}/course/list" class="w3-bar-item w3-button">List</a>
               <a href="${pageContext.request.contextPath}/course/add" class="w3-bar-item w3-button">Add</a>
            </div>
         </div>
         <div class="w3-dropdown-hover">
            <button class="w3-button">Student</button>
            <div class="w3-dropdown-content w3-bar-block w3-card-4">
               <a href="${pageContext.request.contextPath}/student/list" class="w3-bar-item w3-button">List</a>
               <a href="${pageContext.request.contextPath}/student/add" class="w3-bar-item w3-button">Add</a>
            </div>
         </div>
         <div class="w3-dropdown-hover">
            <button class="w3-button">Instructor</button>
            <div class="w3-dropdown-content w3-bar-block w3-card-4">
               <a href="${pageContext.request.contextPath}/instructor/list" class="w3-bar-item w3-button">List</a>
               <a href="${pageContext.request.contextPath}/instructor/add" class="w3-bar-item w3-button">Add</a>
            </div>
         </div>
      </div>
      <div class="w3-container">
         <h2>All Instructors</h2>
         <div class="w3-responsive">
            <table class="w3-table-all">
               <tr class="w3-orange">
                  <th>First Name</th>
                  <th>Last Name</th>
                  <th>Email</th>
                  <th>Actions</th>
               </tr>
               <!-- loop over and print students -->
               <c:forEach items="${instructors}" var="instructor">
                  <!-- construct an "update" link with instructor id -->
                  <c:url var="updateLink" value="/instructor/update" >
                     <c:param name="instructorId" value="${instructor.id }" />
                  </c:url>
                  <c:url var="deleteInstructorLink" value="/instructor/deleteInstructor" >
                     <c:param name="id" value="${instructor.id }" />
                  </c:url>
                  <tr>
                     <td><a href="${instructor.id}">${instructor.firstName}</a></td>
                     <td>${instructor.lastName}</td>
                     <td>${instructor.email}</td>
                     <!-- display update link -->
                     <td><a href="${updateLink}">Update</a>
                        &nbsp;|&nbsp;
                        <a href="${deleteInstructorLink}" onclick="return confirm('Are you sure you want to delete ${instructor.firstName}');">Delete</a>
                     </td>
                  </tr>
               </c:forEach>
            </table>
         </div>
         <p><a href="add">Add instructor</a></p>
      </div>
   </body>
</html>