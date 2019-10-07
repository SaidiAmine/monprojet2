<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="inc/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<title>Affichage Employe</title>
<link type="text/css" rel="stylesheet" href="inc/style.css" />
	
</head>
<body>
	<c:import url="/inc/menuadmin.jsp" />
	<div class="alert span5 alert-success">
		<p>
		<br><span class="succes">Compte créer avec succés</span>
		<br>Type:<span class="info">${emp.type}</span>
		<br>
		Nom: ${emp.nom }<br>
		Prenom: ${emp.prenom }<br>
		Poste: ${emp.poste }<br>
		Agence: ${emp.agence }<br>
		Adresse eloctrinique: ${emp.email }<br>
		Département: ${emp.dept }<br>
		MDP: ${emp.mdp }<br>
		</p>
	</div>
</body>
</html>