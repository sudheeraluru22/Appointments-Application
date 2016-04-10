<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Home</title>
</head>
<body>
	<c:choose>
		<c:when test="${user != null}">
		<div align="center">
			<h1>Hi ! ${user.email} </h1><br/>
			<p><a href="${logout}">Logout</a><br/></p>
			<p><a href="/create">CREATE A NEW APPOINTMENT !</a></p><br />
	    </div>
		</c:when>
		<c:otherwise>
			<div align="center">
				<h1>Welcome to Appointments Manager</h1>
				<br/><br/>
				<a href="${login}">Login or Signup</a>
			</div>
			<br/><br/>
		</c:otherwise>
	</c:choose>
</body>
</html>