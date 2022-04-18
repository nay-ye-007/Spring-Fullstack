<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Registration</title>
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
		<h2>Adding new registration for ${ openClass.course.name }</h2>
		
		<div class="row mt-3">
			<div class="col-4">
				<c:url var="save" value="/registration">
					<c:param name="classId" value="${ openClass.id }"></c:param>
				</c:url>
				<form action="" method="post">
					<div class="mb-3">
						<label for="" class="form-label">Student</label>
						<input type="text" name="student" class="form-control" placeholder="Enter student name" required="required"/>
					</div>
					<div class="mb-3">
						<label for="" class="form-label">Phone</label>
						<input type="tel" name="phone" class="form-control" placeholder="Enter phone number" required="required" />
					</div>
					<div class="mb-3">
						<label for="" class="form-label">Email</label>
						<input type="email" name="email" class="form-control" placeholder="Enter email address" required="required" />
					</div>
					
					<input type="submit" value="Register" class="btn btn-success" />
				</form>
			</div>
		</div>
	</div>
</body>
</html>