<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create New Appointment</title>
</head>
<body>
	<br />
	<c:choose>
		<c:when test="${ ok ==null }">
			<div align="center">
				<h1>Please Login or Signup...</h1>
				<br/><br/>
				<a href="${login}">Login or Signup</a>
			</div>
			<br/><br/>	
		</c:when>
		<c:otherwise>
			<h2 align="center">Page 05</h2>
		</c:otherwise>

	</c:choose>
</body>
</html>