<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
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
         <h2>Welcome to the Colege Management System</h2>
      </div>
   </body>
</html>