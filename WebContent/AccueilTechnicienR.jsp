<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="inc/style.css" />
<title>Accueil Technicien-Responsable</title>
</head>
<body>
<c:import url="/inc/menutechr.jsp" />
	<div class="panel-body">
	<div class="row">
	<div class="col-lg-12">
	<div class="col-md-offset-0 col-md-6">
	<div class="jumbotron">
	<div class="container">
	
	<%-- <h2><span class="label label-success">Accueil pour: ${masession.type }</span></h2>--%>
	<div class="panel panel-primary">
  	<div class="panel-heading">
    	<h3 class="panel-title">Accueil pour: ${masession.type }</h3>
  	</div>
  	<div class="panel-body">Votre id: ${masession.idemp }
	Bienvenue<br>
	${message}<br>
	email: ${masession.email }<br>
	Mot de passe: ${masession.mdp}<br>
	Nom: ${masession.nom}<br>
	Prenom: ${masession.prenom }<br>
	Departement:${masession.dept }
  	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>

</body>
</html>