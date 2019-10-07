<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Assigner Tickets</title>
</head>
<body>
<c:if test="${masession.type=='Technicien-Responsable'}">
<c:import url="/inc/menutechr.jsp" />
<br><br>
	<c:if test="${masession.type=='Technicien-Responsable'}">
	<div class="container">
	<div class="row">
		<div class="col-sm-4">
		<div class = "panel panel-default">
   			<div class = "panel-heading">Techniciens en charge</div>
   			<table class = "table">
		      <tr>
		         <th>Nom</th>
		         <th>Prenom</th>
		         <th>Id</th>
		         <th>Tickets pris en charge</th>
		      </tr>
		      <c:forEach items="${lencharge}" var="element">
		      <tr>
		         <td>${element[0]}</td>
		         <td>${element[1]}</td>
		         <td>${element[2]}</td>
		         <td><span class="badge">${element[3]}</span></td>
		      </tr>
		      </c:forEach>
		   </table>
		</div>
		</div>
		
		<div class="col-sm-4">
		<div class = "panel panel-default">
   			<div class = "panel-heading">Tickets disponibles</div>
   			<table class = "table">
		      <tr>
		         <th>Sujet</th>
		         <th>Desription</th>
		         <th>Priorite</th>
		         <th></th>
		      </tr>
		      <c:forEach items="${lticket}" var="ticket">
		      <c:set  var="prio">
				<c:if test="${ticket.priorite == 1}">Faible</c:if>
				<c:if test="${ticket.priorite == 2}">Moyenne</c:if>
				<c:if test="${ticket.priorite == 3}">Haute</c:if>
			</c:set>
		      <tr>
		         <td>${ticket.sujet}</td>
		         <td>${ticket.description}</td>
		         <td><span class="badge">${prio}</span></td>
		      </tr>
		      </c:forEach>
		   </table>
		</div>
		</div>
		<c:if test="${masession.tech!=1}">
		<div class="col-sm-4">
		<div class = "panel panel-default">
   			<div class = "panel-heading">Valider Technicien Responsable:</div>
				<form method="post" action="assignertechr">
					<ul class="list-group">
						<c:forEach items="${ltechniciens}" var="technicien">
						<input name="id_technicien"type="hidden" value="${technicien.idemp}">
						<button type="submit" class="list-group-item">
				   		<span class="glyphicon glyphicon-chevron-right pull-right"></span>
				      	<p>${technicien.nom}  ${technicien.prenom}  ${technicien.idemp}</p>
				  		</button>
				  		</c:forEach>
			  		</ul>
	  			</form>
	  	</div>
		</div>
		</c:if>
		
		
	</div>		
	
	
	<div class="form-group">
	<form method="post" action="envoiassignation">
		<div class="row">
			<div class="col-sm-4">
			<label for="select">Liste des techniciens: </label>
				<select name="id_technicien" id="select" class="form-control">
				<c:forEach items="${lutilisateur}" var="utilisateur">
	    		<option value="${utilisateur.idemp}">${utilisateur.nom} ${utilisateur.prenom}</option>
	    		</c:forEach>
				</select>
			</div>
			<div class="col-sm-4">
			<label for="select">Tickets en attente: </label>
				<select name="id_ticket" id="select" class="form-control">
					<c:forEach items="${lticket}" var="ticket">
						<c:set  var="prio">
							<c:if test="${ticket.priorite == 1}">Faible</c:if>
							<c:if test="${ticket.priorite == 2}">Moyenne</c:if>
							<c:if test="${ticket.priorite == 3}">Haute</c:if>
						</c:set>
				    <option value="${ticket.id_ticket}">${ticket.sujet} - ${prio}</option>
				    </c:forEach>
				</select>
			</div>
	</div>
		<div class="row">
		<br>
		<div class="col-sm-4">
		<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-ok"></span>  Assigner ticket</button>
		</div>
		</div>
		</form>
	</div>
	</div>
</c:if>
</c:if>
</body>
</html>