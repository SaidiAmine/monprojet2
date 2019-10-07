<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="inc/style.css" />
<title>Insert title here</title>
</head>
<body>
<br>
	<form  method="post" action="creerfaq">
	<fieldset>
	<legend>Créeation d'une question Réponse.</legend>
	<span class="info">Question:</span><br>
	<textarea class="form-control" rows="5" cols="36" name="question">${param.question}</textarea> <br>
	
					<div class="form-group">
                    <label for="texte">Sujet</label>
                	<input type="text" id="sujet" name="sujet" value="${param.sujet}" size="20" maxlength="100" class="form-control">
                		<c:if test="${not empty erreurs }">
							<div class="alert span5 alert-danger">
					    	<strong>${erreurs['sujet']}</strong>
							</div>
			      		</c:if>
                	</div>
                		
	<span class="info">Réponse:</span><br>
	<textarea class="form-control" rows="5" cols="36" name="reponse">${param.reponse}</textarea><span class="erreur">${erreurs['reponse'] }</span> <br>
	<input type="hidden" name="id_employe" value="${masession.idemp}">
	<input type="submit">
	</fieldset>
	</form>
</body>
</html>