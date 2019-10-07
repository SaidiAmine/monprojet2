<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="inc/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<title>Création d'un ticket</title>
<link type="text/css" rel="stylesheet" href="inc/style.css" />
</head>
<body>
<c:import url="/inc/menuemp.jsp" />
	 <div>
            <form method="post" action="creerticket" enctype="application/x-www-form-urlencoded" class="col-lg-6">
            <%--^ Les données seront envoyés a la servlet CreationTicket  --%>
            ${masession.idemp}
                <fieldset>
                    <legend>Création d'un ticket</legend>
    
    				 <div class="form-group">
                    <label for="texte">Sujet</label>
                	<input type="text" id="sujet" name="sujet" value="${param.sujet}" size="20" maxlength="100" class="form-control">
                		<c:if test="${not empty erreurs }">
							<div class="alert span5 alert-danger">
					    	<strong>${erreurs['sujet']}</strong>
							</div>
			      		</c:if>
                	</div>
                	
                    <div class="form-group">
                    <label for="texte">Description</label>
                    <textarea rows="5" cols="36" type="textarea" id="textarea" name="description" class="form-control">${param.description}</textarea>
                    	<c:if test="${not empty erreurs }">
							<div class="alert span5 alert-danger">
					    	<strong>${erreurs['description']}</strong>
							</div>
			      		</c:if>
                    </div>
                    
                    <div class="form-group">
      				<label for="select">Priorité : </label>
      				<select id="select" class="form-control" name="priorite">
        				<option value="1">Faible</option>
        				<option value="2">Moyenne</option>
        				<option value="3">Haute</option>
      				</select>
    				</div>
                	<input type="hidden" name="idemp" value="${masession.idemp}">
                </fieldset>
                <button type="submit" class="btn btn-primary btn-primary"><span class="glyphicon glyphicon-ok"></span>  Valider</button>
                <button type="reset" class="btn btn-primary btn-primary"><span class="glyphicon glyphicon-repeat"></span>  Remettre à zero</button>
            </form>
        </div>
</body>
</html>