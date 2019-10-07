<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Foire aux questions</title>
</head>
<body>
<c:if test="${masession.type=='Employe'}"><c:import url="/inc/menuemp.jsp" /></c:if>
<c:if test="${masession.type=='Technicien'}"><c:import url="/inc/menutech.jsp" /></c:if>
<c:if test="${masession.type=='Technicien-Responsable'}"><c:import url="/inc/menutechr.jsp" /></c:if>
<c:if test="${masession.type=='Admin'}"><c:import url="/inc/menuadmin.jsp" /></c:if>
	<c:if test="${masession.type=='Technicien' || masession.type=='Technicien-Responsable'}">
	<br><br>
	<div class="container">
	<div class="row">
	<div class="col-md-5">
		<form  method="post" action="creerfaq">
		<fieldset>
		<br><br>
		<legend><strong>Créeation d'une question Réponse.</strong></legend>
		<div class="form-group">
		<span class="info">Question:</span><br>
		<textarea class="form-control" rows="5" cols="36" name="question">${param.question}</textarea><br>
		
                		<c:if test="${not empty erreurs }">
							<div class="alert span5 alert-danger">
					    	<strong>${erreurs['question']}</strong>
							</div>
			      		</c:if>
                	
                	
		<span class="info">Réponse:</span><br>
		<textarea class="form-control" rows="5" cols="36" name="reponse">${param.reponse}</textarea><br>
		
						<c:if test="${not empty erreurs }">
							<div class="alert span5 alert-danger">
					    	<strong>${erreurs['reponse']}</strong>
							</div>
			      		</c:if>
		
		<input type="hidden" name="id_employe" value="${masession.idemp}">
		<button type="submit" class="btn btn-block btn-sm btn-primary"><span class="glyphicon glyphicon-plus"></span> Ajouter Question Réponse</button>
		</div>
		</fieldset>
		</form>
	</div>
	</div>
	</div>
	</c:if>
	<br><br>
	
	<div class="container">
	<div class="row">
	<div class="col-md-5">
		<fieldset>
		<legend><strong>Liste des Questions/Réponses.</strong></legend>
		</fieldset>
	</div>
	</div>
	</div>
	
	<c:forEach items="${lfaq}" var="faq" varStatus="boucle">
	<br><br>
	<div class="container">
	<div class="row">
	<div class="col-md-5">
	
	<div class="panel panel-primary">
  	<div class="panel-heading">
  	<h3 class="panel-title"><strong>Question: </strong>${faq.question}</h3>
  	</div>
  	<div class="panel-body">
  		${faq.reponse}
  	</div>
  	</div>
  	</div>
  	<c:if test="${masession.type=='Technicien' || masession.type=='Technicien-Responsable'}">
  			
  			<div class="col-md-5">
	  			<div class="btn-group">
		  			<form method="post" action="supprimerqr">
		  			<input type="hidden" name="id_qr" value="${faq.id_qr}">
		  			<button type="submit" class="btn btn-xs btn-primary"><span class="glyphicon glyphicon-remove"></span>Supprimer</button>
		  			<br>
		  			</form>
		  			<form method="post" action="modifierqr">
		  			<input type="hidden" name="id_qr" value="${faq.id_qr}">
		  			<button type="submit" class="btn btn-xs btn-primary"><span class="glyphicon glyphicon-refresh"></span>Modifier</button>
		  			</form>
		  		</div>
	  		</div>
  			
  		</c:if>
  	
  	</div>
  	</div>
	</c:forEach>
</body>
</html>