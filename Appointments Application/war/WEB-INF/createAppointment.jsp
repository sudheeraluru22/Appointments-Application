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
			<div align="center" action="/">
				<form method="post">
					<br /> <br />
					<h2>Create Appointment</h2>
					<br /> <input type="text" name="name" /><br /><br /> 
					<input type="text" name="date" /><br /><br />
					<input type="text" name="time" /><br /><br /> 
					<input name="add" type="submit" /><br /><br />
				</form>
				<a href="/">cancle</a><br /><br />
			</div>
		</c:otherwise>

	</c:choose>
</body>
</html>