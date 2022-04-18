<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Using IOC Container</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
</head>
<body>
	
	<div class="container mt-4">
		<div class="bg-success">
			<h2 class="p-3 text-white">All Courses</h2>
		</div>
		<a href="/course-edit" class="btn btn-success mt-3">Add New Course</a>
		
		<div class="mt-2">
			<c:choose>
				<c:when test="${ empty courses }">
					<div class="alert alert-warning">
						There is no course. Please add new course.
					</div>
				</c:when>
				<c:otherwise>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>ID</th>
								<th>Name</th>
								<th>Fees</th>
								<th>Duration</th>
								<th>Description</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="c" items="${ courses }">
								<tr>
									<td>${ c.id }</td>
									<td>${ c.name }</td>
									<td>${ c.fees }</td>
									<td>${ c.duration } Months</td>
									<td>${ c.description }</td>
									<td>
										<c:url var="classes" value="/classes">
											<c:param name="courseId" value="${ c.id }"></c:param>
										</c:url>
										<a href="${ classes }" class="link-success" style="text-decoration: none;">Open Classes</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
	</div>
	</div>
	
	
</body>
</html>