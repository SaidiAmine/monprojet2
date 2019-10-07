<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consulter tickets</title>
</head>
<body>
${masession.idemp}
<form method="post" action="consultertickets">
<input type="hidden" name="idemp" value="${masession.idemp}">
<input type="submit" name="Consulter Tickets" value="Consulter Tickets"/>
</form>
</body>
</html>