<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script>
            $(document).ready(function(){
                	$(".btnSelect").click(function(){
                	var currentRow=$(this).closest("tr"); 
                    
                    var data=currentRow.find("td:eq(3)").text(); // 
                    
                    window.location = '/monprojet2/reponsesticket?id_ticket=' + data;
                });
            });
</script>
<meta http-equiv="Content-Type" content="text/html">
<link type="text/css" rel="stylesheet" href="inc/style.css" />
<title>liste tickets</title>
</head>
<body>
<c:import url="/inc/menuemp.jsp" />
${masession.idemp}
    	
    		<%-- ECHANTILLON:

    		<tr class="warning">
       	 	<td>Indonésie et Malaisie</td>
        	<td>Pâte à papier, l’huile de palme et le caoutchouc</td>
      		</tr>

    		 --%>
    	
            <table id="myTable" class="table table-bordered table-striped table-condensed">
                <tr>
                    <th>Sujet</th>
                    <th>Description</th>
                    <th>Priorite</th>
                    <th>ID Ticket</th>
                    <th>Etat</th>
                    <th>Date Soumise</th>
                    <th>Action</th>                   
                </tr>
                <c:forEach items="${lticket }" var="ticket" varStatus="boucle">
                <c:set var="valeure">
                	<c:choose>
                	<c:when test="${ticket.etat == 1}">active</c:when>
                	<c:when test="${ticket.etat == 2}">success</c:when>
                	<c:when test="${ticket.etat == 3}">danger</c:when>
                	</c:choose>
                </c:set>
                <tr class="${valeure}">
                    <td><c:out value="${ ticket.sujet }"/></td>
                    <td><c:out value="${ ticket.description }"/></td>
                     <c:choose>
                     	<c:when test="${ticket.priorite == 1 }">
                     		<td><c:out value="Faible"/></td>
                     	</c:when>
                     	<c:when test="${ticket.priorite == 2 }">
                     		<td><c:out value="Moyenne"/></td>
                     	</c:when>
                     	<c:when test="${ticket.priorite == 3 }">
                     		<td><c:out value="Haute"/></td>
                     	</c:when>
                     	<c:otherwise>
                     		<td><c:out value="Priorite non-défini"/></td>
                     	</c:otherwise>
                     </c:choose>
                  
                     <td><c:out value="${ ticket.id_ticket }"/></td>
                     <c:choose>
                     	<c:when test="${ticket.etat == 1 }">
                     		<td><c:out value="En attente de prise en charge"/></td>
                     	</c:when>
                     	<c:when test="${ticket.etat == 2 }">
                     		<td><c:out value="En attente de votre validation"/></td>
                     	</c:when>
                     	<c:when test="${ticket.etat == 3 }">
                     		<td><c:out value="Cloturé"/></td>
                     	</c:when>
                     	<c:when test="${ticket.etat == 4 }">
                     		<td><c:out value="En cours de traitement"/></td>
                     	</c:when>
                     	<c:otherwise>
                     		<td><c:out value="Etat du ticket non-défini"/></td>
                     	</c:otherwise>
                     </c:choose>
                     <td><c:out value="${ ticket.date_envoi }"/></td>
                     <td><button class="btnSelect" type="button" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span>  Vérifier</button></td>
 				</tr>
                </c:forEach>
            </table>
</body>
</html>