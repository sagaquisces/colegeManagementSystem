<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
      <title>${instructor.firstName} ${instructor.lastName}</title>
   </head>
   <body>
      <c:if test="${instructor.firstName != null}">
         <c:url var="updateLink" value="/instructor/update" >
            <c:param name="instructorId" value="${instructor.id }" />
         </c:url>
         <c:url var="deleteInstructorLink" value="/instructor/deleteInstructor" >
            <c:param name="id" value="${instructor.id }" />
         </c:url>
         <c:url var="deleteInstructorDetailLink" value="/instructor/deleteInstructorDetail" >
            <c:param name="id" value="${instructor.instructorDetail.id }" />
         </c:url>
         <c:choose>
            <c:when test="${instructor.courses[0] != null}">
               <p>${instructor.firstName} ${instructor.lastName} likes to ${instructor.instructorDetail.hobby} detail page.</p>
               <p>The classes I teach:</p>
               <ul>
                  <c:forEach items="${instructor.courses}" var="course">
                     <li><em>${course.code}</em> ${course.title}
                  </c:forEach>
               </ul>
            </c:when>
            <c:otherwise>
               <p>I don't teach any courses yet.</p>
            </c:otherwise>
         </c:choose>
         <a href="${updateLink}">Update</a>
         &nbsp;|&nbsp;
         <a href="${deleteInstructorLink}" onclick="return confirm('Are you sure you want to delete ${instructor.firstName}');">Delete Instructor</a>
         &nbsp;|&nbsp;
         <a href="${deleteInstructorDetailLink}" onclick="return confirm('Are you sure you want to delete details for ${instructor.firstName}');">Delete Instructor Detail</a>
      </c:if>
   </body>
</html>