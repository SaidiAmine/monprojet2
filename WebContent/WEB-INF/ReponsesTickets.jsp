<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Réponses ticket</title>
<meta http-equiv="Content-Type" content="text/html">
<link type="text/css" rel="stylesheet" href="inc/style.css" />
<link href="inc/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<c:if test="${masession.type=='Employe'}"><c:import url="/inc/menuemp.jsp" /></c:if>
<c:if test="${masession.type=='Technicien'}"><c:import url="/inc/menutech.jsp" /></c:if>
<c:if test="${masession.type=='Technicien-Responsable'}"><c:import url="/inc/menutechr.jsp" /></c:if>
<c:if test="${masession.type=='Admin'}"><c:import url="/inc/menuadmin.jsp" /></c:if>
	<br>
	<div class="panel-body">
	<div class="row">
	<div class="col-lg-12">
	<div class="col-md-offset-0 col-md-6">
	<div class="container">
	<div class="panel panel-primary">
  	<div class="panel-heading">
  	<h3 class="panel-title"><strong>Réponses pour tickets id:</strong> ${ticket.id_ticket}</h3>
  	</div>
  		<div class="panel-body">
  		<c:if test="${ticket.etat==3}"><h3><span class="label label-success">Ticket Validé</span></h3></c:if>
  		<strong>Sujet:</strong> ${ticket.sujet} <br>
		<strong>Description:</strong> ${ticket.description}<br>
  	</div>
  	</div>
  	</div>
  	</div>
  	</div>
  	</div>
  	</div>
  	
  	<c:forEach items="${lreponse}" var="reponse">
		<div class="panel-body">
		<div class="row">
		<div class="col-lg-12">
		<div class="col-md-offset-0 col-md-6">
		<div class="container">
		<div class="panel panel-primary">
  		<div class="panel-heading">
    	<h3 class="panel-title"><strong>Nom:</strong> ${reponse[0]} <strong>Prenom: </strong> ${reponse[1]}</h3>
  		</div>
  		<div class="panel-body">
  		<c:if test="${reponse[7]==1}"><h3><span class="label label-success">Réponse Validé</span></h3></c:if>
  		<br><strong>Réponse: </strong>${reponse[2]}<br>
		<strong>Date: </strong> ${reponse[3] }<br>
		<strong>Type: </strong> ${reponse[4] }<br>
		<c:if test="${ticket.etat==1 || ticket.etat==2}">
			<c:if test="${masession.type=='Employe'}">
				<c:if test="${masession.idemp==ticket.id_employe}"> <%-- TEST ID connecté le meme qui as posté le ticket --%>
					<c:if test="${reponse[4]=='Technicien' || reponse[4]=='Technicien-Responsable' }">
					<form method="post" action="validerticket">
					<input type="hidden" name="id_ticket" value="${ticket.id_ticket}">
					<input type="hidden" name="id_reponse" value="${reponse[6]}">
					<br>
					<button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-ok"></span>   Valider</button>
					</form>
					</c:if>
				</c:if>
			</c:if>
		</c:if>
  		</div>
  		</div>
  		</div>
  		</div>
  		</div>
  		</div>
  		</div>
	</c:forEach>
  	<%-- ===============================================================- ---%>
	<c:if test="${ticket.etat!=3 && ticket.etat !=5}">
	<form class="col-lg-6" method="post" action="creerreponse" enctype="application/x-www-form-urlencoded">
  	<h3><span class="label label-info">Répondre:</span></h3>
    <div class="form-group">
    	<label for="textarea">Texte</label>
	    <textarea id="textarea" type="textarea" class="form-control" name="texte"></textarea>
	    	<c:if test="${not empty erreurs }">
			<div class="alert span5 alert-danger">
	    	<strong>${erreurs['texte']}</strong>
			</div>
      		</c:if>
    </div>
    	<input type="hidden" name="id_employe" value="${masession.idemp}">
		<input type="hidden" name="id_ticket" value="${ticket.id_ticket}">
		<input type="hidden" name="type" value="${masession.type}">
		<button type="submit" class="btn btn-primary">Envoyer</button>
	</form>
	</c:if>
<%--
<p>Test <br>votre id:${masession.idemp}<br>
Type:${masession.type}<br>
</p>
	 <fieldset>
	<legend>Réponses pour ticket id: ${ticket.id_ticket}</legend><br>
	<c:if test="${ticket.etat==3}"><span class="succes">Ticket Validé</span></c:if>
	<p>Sujet:</p> ${ticket.sujet} <br>
	<p>Description:</p> ${ticket.description}<br>
	</fieldset>
	<br>
	
	<c:forEach items="${lreponse}" var="reponse">
		<fieldset>
		<c:if test="${reponse[7]=='1'}"><span class="succes">Réponse Validé</span></c:if>
		<p>Nom: ${reponse[0]}</p>
		<p>Prenom: ${reponse[1] }</p>
		<p>Réponse: ${reponse[2] }</p>
		<p>Date: ${reponse[3] }</p>
		<p>Type: ${reponse[4] }</p>
		
		<c:if test="${ticket.etat==1 || ticket.etat==2}">
		<c:if test="${masession.type=='Employe'}">
		<c:if test="${masession.idemp==ticket.id_employe}"> 
			<c:if test="${reponse[4]=='Technicien'}">
				 <button class="btnSelect">Valider Reponse</button>
			<form method="post" action="validerticket">
			<input type="hidden" name="id_ticket" value="${ticket.id_ticket}">
			<input type="hidden" name="id_reponse" value="${reponse[6]}">
			<input type="submit" value="Valider Ticket">
			</form>
			</c:if>
			</c:if>
		</c:if>
		</c:if>
		</fieldset>
		<br><br>
	</c:forEach>
	
	<br><br><br>
	
	<fieldset>
	<legend>Répondre:</legend>
	<form method="post" action="creerreponse" enctype="application/x-www-form-urlencoded">
		<textarea rows="5" cols="36" name="texte"></textarea><span class="erreur">${erreurs['texte']}</span><br/>
		<input type="hidden" name="id_employe" value="${masession.idemp}">
		<input type="hidden" name="id_ticket" value="${ticket.id_ticket}">
		<input type="hidden" name="type" value="${masession.type}">
		<input type="submit" value="Valider"><br/>
	</form>
	<p></p>
	</fieldset>
</body>
</html>--%>