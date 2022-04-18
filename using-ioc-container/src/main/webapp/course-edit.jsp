<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Course Edit</title>
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
		<h2 class="mb-4">Adding New Course</h2>
		
		<div class="row">
			<div class="col-4">
				<c:url var="save" value="/courses"></c:url>
				<form action="${ save }" method="post">
					<div class="mb-3">
						<label for="" class="form-label">Course Name</label>
						<input type="text" name="name" class="form-control" placeholder="Enter course name" required="required"/>
					</div>
					<div class="mb-3">
						<label for="" class="form-label">Duration</label>
						<input type="number" name="duration" class="form-control" placeholder="Enter course duration" required="required"/>
					</div>
					<div class="mb-3">
						<label for="" class="form-label">Fees</label>
						<input type="number" name="fees" class="form-control" placeholder="Enter course fees" required="required"/>
					</div>
					<div class="mb-3">
						<label for="" class="form-label">Description</label>
						<textarea rows="4" cols="40" name="description" class="form-control"></textarea>
					</div>
					
					<input type="submit" value="Add Course" class="btn btn-success"/>
				</form>
			</div>
		</div>
	</div>
</body>
</html>