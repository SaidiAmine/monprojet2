<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="inc/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<title>liste tickets</title>
</head>
<body>
<c:if test="${masession.type=='Technicien'}"><c:import url="/inc/menutech.jsp" /></c:if>
<c:if test="${masession.type=='Technicien-Responsable'}"><c:import url="/inc/menutechr.jsp" /></c:if>
<c:if test="${masession.type=='Employe'}"><c:import url="/inc/menuemp.jsp" /></c:if>
<br><br><br>
    		<div class="alert span5 alert-danger">
			<strong>Vous n'avez pas de ticket</strong>
			</div>
</body>
</html>