<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="inc/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
<div class="container">
        <div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-6">
								<a href="#" class="active" id="login-form-link">Connexion</a>
							</div>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
							<c:if test="${validitee==false}">
									<div class="alert span5 alert-danger">
							    	<strong>Vous avez saisi un mauvais mot de passe / e-mail</strong>
									</div>
					      		</c:if>
								<form id="login-form" action="connexion" method="post" style="display: block;">
									<c:choose>
									<c:when test="${empty erreurs}">
										<div class="form-group">
											<input type="text" name="email" id="email" tabindex="1" class="form-control" placeholder="e-mail" value=""><span class="erreur">${erreurs['email']}</span>
										</div>
									
										<div class="form-group">
											<input type="password" name="mdp" id="mdp" tabindex="2" class="form-control" placeholder="mot de passe"><span class="erreur">${erreurs['mdp']}</span>
										</div>
									
									</c:when>
									<c:otherwise>
									
									<div class="form-group has-error has-feedback">
      								<label class="control-label" for="idError">${erreurs['email'] }</label>
     								<input type="text" class="form-control" name="email" placeholder="e-mail" id="idError">
      								<span class="glyphicon glyphicon-remove form-control-feedback"></span>
      								<span class="help-block">Corrigez l'erreur s'il vous plait</span>
   									</div>
									
									<div class="form-group has-error has-feedback">
      								<label class="control-label" for="idError">${erreurs['mdp'] }</label>
     								<input type="password" class="form-control" name="mdp" placeholder="mot de passe" id="idError">
      								<span class="glyphicon glyphicon-remove form-control-feedback"></span>
      								<span class="help-block">Corrigez l'erreur s'il vous plait</span>
   									</div>
									
									</c:otherwise>
									</c:choose>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" value="Connexion">
											</div>
										</div>
									</div>
									</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="inc/style.css" />
</head>
<body>
	 <div>
            <form method="post" action="connexion">
            
                <fieldset>
                    <legend>Informations d'authentification</legend>
    				<span class="erreur">${message}</span><br>
                    <label for="email">Adresse email</label>
                    <input type="text" id="email" name="email" value="${param.email}" size="20" maxlength="60" />
                    <span class="erreur">${erreurs['email']}</span>
                    <br>
                    
                  	<label for="mdp">Mor de passe</label>
                    <input type="password" id="mdp" name="mdp" value="" size="20" maxlength="60" />
                    <span class="erreur">${erreurs['mdp']}</span>
                    <br />
                </fieldset>
                <input type="submit" value="Valider"  />
            </form>
        </div>
</body>
</html> --%>
