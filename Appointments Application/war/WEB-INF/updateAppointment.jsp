<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Existing Appointment</title>

</head>

<body>
	<br />
	<c:choose>
		<c:when test="${ ok==null }">
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
					<h2>Update Appointment</h2>
					<br /> 
					<input type="text" name="Name" value=<%=request.getAttribute("Name")%> /><br /> 
				    <input type="text" name="Date" value=<%=request.getAttribute("Date")%> /><br />
					<input type="text" name="Time" value=<%=request.getAttribute("Time")%> /><br /> <input name="enter" type="submit" />
				</form><br /> <br />
			</div><br /><br />
		</c:otherwise>
	</c:choose>
</body>
</html>