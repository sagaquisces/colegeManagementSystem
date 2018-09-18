<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
      <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
      <style>
         /* The container */
         .myCheckboxContainer {
             display: block;
             position: relative;
             padding-left: 35px;
             /* margin-bottom: 12px; */
             cursor: pointer;
             /* font-size: 22px; */
             -webkit-user-select: none;
             -moz-user-select: none;
             -ms-user-select: none;
             user-select: none;
         }
         
         /* Hide the browser's default checkbox */
         .myCheckboxContainer input {
             position: absolute;
             opacity: 0;
             cursor: pointer;
         }
         
         /* Create a custom checkbox */
         .checkmark {
             position: absolute;
             top: 0;
             left: 0;
             height: 20px;
             width: 20px;
             background-color: #eee;
         }
         
         /* On mouse-over, add a grey background color */
         .myCheckboxContainer:hover input ~ .checkmark {
             background-color: #ccc;
         }
         
         /* When the checkbox is checked, add a blue background */
         .myCheckboxContainer input:checked ~ .checkmark {
             background-color: #2196F3;
         }
         
         /* Create the checkmark/indicator (hidden when not checked) */
         .checkmark:after {
             content: "";
             position: absolute;
             display: none;
         }
         
         /* Show the checkmark when checked */
         .myCheckboxContainer input:checked ~ .checkmark:after {
             display: block;
         }
         
         /* Style the checkmark/indicator */
         .myCheckboxContainer .checkmark:after {
             left: 9px;
                top: 5px;
                width: 5px;
                height: 10px;
                border: solid white;
                border-width: 0 3px 3px 0;
                -webkit-transform: rotate(45deg);
                -ms-transform: rotate(45deg);
                transform: rotate(45deg);
         }
      </style>
      <title>${student.firstName} ${student.lastName}</title>
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
         <h2>Student Detail for: ${student.firstName} ${student.lastName}</h2>
         <p><em>Currently enrolled in:</em></p>
         <ul class="w3-ul w3-border">
            <c:forEach items="${student.courses}" var="course">
               <li>${course.title}<span class="w3-tag w3-blue w3-round w3-right w3-margin-right w3-hide-small">${course.code}</span></li>
            </c:forEach>
         </ul>
         <p><em>Courses you can take:</em></p>
         <form action="addCourse">
            <input type="hidden" name="studentId" value="${student.id}"/>
            <ul class="w3-ul w3-border">
               <c:forEach items="${courses}" var="availableCourse">
                  <li>
                     <label class="myCheckboxContainer">${availableCourse.title}
                        <input type="checkbox" name="checkedCourse" value="${availableCourse.id}"/>
                        <span class="checkmark"></span>
                        <span class="w3-tag w3-blue w3-round w3-right w3-margin-right w3-hide-xsmall">${availableCourse.code}</span>
                     </label>
                  </li>
               </c:forEach>
               
            </ul>
            <input type="submit" />
         </form>
      </div>
   </body>
</html>