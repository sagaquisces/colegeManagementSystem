<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
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
               <h2>Add Instructor</h2>
            </div>
            <form:form action="process" class="w3-container" modelAttribute="instructor" method="POST">
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
               <p><a href="${pageContext.request.contextPath}/instructor/list">Return to instructor list</a></p>          
               
            </form:form>
         </div>
      </div>
   </body>
</html>