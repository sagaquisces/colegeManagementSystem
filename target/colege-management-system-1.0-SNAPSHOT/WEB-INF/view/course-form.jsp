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
      <title>Add Course</title>
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
         <div class="w3-card-4 w3-margin-top">
            <div class="w3-container w3-orange">
               <h2>Add Course</h2>
            </div>
            <form:form action="process" class="w3-container" modelAttribute="course"  method="POST">
               <!-- for update, otherwise 0 -->
               <form:hidden path="id" />
               <div class="w3-row-padding">
                  <p class="w3-third">
                     <form:input class="w3-input" path="code" type="text" name="code" placeholder="Course Code"/>
                     <form:errors path="code" />
                  </p>
                  <p class="w3-twothird">
                     <form:input class="w3-input" path="title" type="text" name="title" placeholder="Course Title"/>
                  </p>
               </div>
               <p class="w3-row-padding">
                  <label>Instructor</label>
                  <form:select class="w3-select" path="instructor.id">
                     <form:option value="0"><em>No instructor selected. Please choose one.</em></form:option>
                     <!-- need to associate form with instructor id -->
                     <c:forEach items="${instructors}" var="instructor">
                        <c:choose>
                           <c:when test="${instructor.id == course.instructor.id}">
                              <form:option selected="true" value="${instructor.id}">
                                 ${instructor.firstName } ${instructor.lastName}
                              </form:option>
                           </c:when>
                           <c:otherwise>
                              <form:option value="${instructor.id}">
                                 ${instructor.firstName } ${instructor.lastName}
                              </form:option>
                           </c:otherwise>
                        </c:choose>
                     </c:forEach>
                  </form:select>
               </p>
               <p class="w3-row-padding">
                  <input class="w3-btn w3-orange" type="submit"/>
               </p>
               <p><a href="${pageContext.request.contextPath}/course/list">Return to course list</a></p>          
            </form:form>
         </div>
      </div>
   </body>
</html>
