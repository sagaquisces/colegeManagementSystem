<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
      <style>

         .video-responsive{
             overflow:hidden;
             padding-bottom:56.25%;
             position:relative;
             height:0;
         }
         .video-responsive iframe{
             left:0;
             top:0;
             height:100%;
             width:100%;
             position:absolute;
         }

      </style>
      <title>${instructor.firstName} ${instructor.lastName}</title>
   </head>
   <body>
      <div class="w3-container w3-black">
         <h2>Welcome to the Colege Management System</h2>
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
         
         <c:choose>
            <c:when test="${instructor.firstName != null}">
               <h2>Instructor Detail for: ${instructor.firstName} ${instructor.lastName}</h2>
            
               <h3>Classes I teach:</h3>
               <c:choose>
                  <c:when test="${instructor.courses[0] != null}">
                     <ul class="w3-ul w3-border">
                        <c:forEach items="${instructor.courses}" var="course">
                           <li>${course.title}<span class="w3-tag w3-blue w3-round w3-right w3-margin-right w3-hide-small">${course.code}</span></li>
                        </c:forEach>
                     </ul>
                  </c:when>
                  <c:otherwise>
                     <p>I don't teach any courses yet.</p>
                  </c:otherwise>
               </c:choose>
               <c:url var="updateLink" value="/instructor/update" >
                  <c:param name="instructorId" value="${instructor.id }" />
               </c:url>
               <c:url var="deleteInstructorLink" value="/instructor/deleteInstructor" >
                  <c:param name="id" value="${instructor.id }" />
               </c:url>
               <c:url var="deleteInstructorDetailLink" value="/instructor/deleteInstructorDetail" >
                  <c:param name="id" value="${instructor.instructorDetail.id }" />
               </c:url>
               
               <h3>More about me:</h3>
        
               <div class="video-responsive">
                  <iframe width="560" height="315" src="https://www.youtube.com/embed/${instructor.instructorDetail.youtubeChannel}" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
               </div>
               <p><a href="${updateLink}">Update</a>
               &nbsp;|&nbsp;
               <a href="${deleteInstructorLink}" onclick="return confirm('Are you sure you want to delete ${instructor.firstName}');">Delete Instructor</a>
               &nbsp;|&nbsp;
               <a href="${deleteInstructorDetailLink}" onclick="return confirm('Are you sure you want to delete details for ${instructor.firstName}');">Delete Instructor Detail</a></p>
            </c:when>
            <c:otherwise>
               <p><em>No instructor with this id.</em></p>
            </c:otherwise>
         </c:choose>

      </div>

   </body>
</html>