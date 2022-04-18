<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Class</title>

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
		<h2>Adding new class for ${ course.name }</h2>
		
		<div class="row mt-3">
			<div class="col-4">
				<c:url var="save" value="/classes">
					
				</c:url>
				<form action="" method="post">
					<div class="mb-3">
						<label class="form-label">Teacher</label>
						<input type="text" name="teacherName" class="form-control" placeholder="Enter teacher name" required="required"/>
					</div>
					<div class="mb-3">
						<label class="form-label">Start Date</label>
						<input type="date" name="startDate" class="form-control" placeholder="Enter start date" required="required"/>
					</div>
					
					<input type="submit" value="Create Class" class="btn btn-success"/>
				</form>
			</div>
		</div>		
	</div>
</body>
</html>