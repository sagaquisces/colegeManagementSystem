<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
      <title>${course.title} Reviews</title>
   </head>
   <body>
      <div class="w3-container">
         <h2>${course.title} Reviews</h2>
         <c:if test="${course.reviews[0] == null}">
            <p><em>There are no reviews yet.</em></p>
         </c:if>
         <c:forEach items="${course.reviews}" var="review">
            <!-- construct an "update" link with course id -->
            <c:url var="deleteReviewLink" value="reviews/deleteReview" >
               <c:param name="reviewId" value="${review.id}" />
               <c:param name="courseId" value="${course.id}" />
            </c:url>
            <div class="w3-panel w3-leftbar w3-light-grey">
               <p class="w3-large w3-serif"><i>"${review.comment}"</i></p>
               <p><a href="${deleteReviewLink}">Delete review</a></p>
            </div>
         </c:forEach>
         <c:url var="addLink" value="reviews/add" >
            <c:param name="courseId" value="${course.id}" />
         </c:url>
         <p><a href="${addLink}">Add a review</a></p>
         <p><a href="list">Return to Course List</a></p>
      </div>
   </body>
</html>