<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="inc/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<title>Accueil Employe</title>
</head>
<body>
<c:import url="/inc/menuemp.jsp" />
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
  	<%-- Idemp - nom - prenom - agence - poste - email - mdp - departement - date_inscr - type  --%>
  	<div class="panel-body">Votre id: ${masession.idemp }
	Bienvenue<br>
	${message}<br>
	<strong>email:</strong> ${masession.email }<br>
	<strong>Nom:</strong> ${masession.nom}<br>
	<strong>Prenom:</strong> ${masession.prenom }<br>
	<strong>Departement:</strong> ${masession.dept }<br>
	<strong>Agence:</strong> ${masession.agence}<br>
	<strong>Poste:</strong> ${masession.poste}<br>
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