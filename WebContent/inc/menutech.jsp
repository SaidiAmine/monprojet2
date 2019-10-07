<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="inc/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<div class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Help Desk</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="/monprojet2/connexion">Accueil   <span class="glyphicon glyphicon-user"></span></a></li>
            <li><a href="javascript:document.getElementById('form2').submit()">Depot Tickets   <span class="glyphicon glyphicon-inbox"></span></a></li>
          	<li><a href="javascript:document.getElementById('form3').submit()">FAQ  <span class="glyphicon glyphicon-question-sign"></span></a></li>
          	<li class="active"><a href="/monprojet2/deconnexion">Deconnexion   <span class="glyphicon glyphicon-off"></span></a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
      </div>
	
	<div>
	<form action="<c:url value="/afficherfaq"/>" method="post" id="form3">
	<input type="hidden" name="idemp" value="${masession.idemp}">
	</form>
	</div>
	
	<div>
	<form action="<c:url value="/depottickets"/>" method="post" id="form2">
	<input type="hidden" name="id_technicien" value="${masession.idemp}">
	</form>
	</div>







<fieldset>
<div id="menu">
<form method="post" action="assignerticket">
<input type="hidden" name="idemp" value="${masession.idemp}">
<input type="submit" name="Assigner Ticket" value="Assigner Ticket"/>
</form>
</div>
</fieldset>