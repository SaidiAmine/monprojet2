<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="inc/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestion des comptes</title>
</head>
<body>
<c:import url="/inc/menuadmin.jsp"/>
<br><br>
<div class="container">
<div class="col-sm-4">
		<div class = "panel panel-default">
   			<div class = "panel-heading">Suppression de comptes:</div>
				
					<ul class="list-group">
						<c:forEach items="${lusers}" var="user">
						<form method="post" action="supprimercompte">
						<input name="id_employe"type="hidden" value="${user.idemp}">
						<button type="submit" class="list-group-item">
				   		<span class="glyphicon glyphicon-trash"></span>   ${user.nom}  ${user.prenom} - ${user.idemp} - ${user.type}</button>
				  		</form>
				  		</c:forEach>
			  		</ul>
	  	</div>
</div>
<div class="col-sm-4">
<form method="post" action="creeremploye">
            <%--^ Les données seront envoyés a la servlet CreationEmploye  --%>
                <fieldset>
                	<p>${message}</p>
                    <legend>Informations employé</legend>
    				
    				<div class="form-group">
                    <label for="nom">Nom d'utilisateur</label>
                	<input type="text" class="form-control" id="nom" name="nom" value="${param.nom}" size="20" maxlength="20" />
                	<span class="erreur">${erreurs['nom']}</span>
                	
                	<br />
                    
                    
                    <label for="prenom">Prénom <span class="requis">*</span></label>
                    <input type="text" class="form-control" id="prenom" name="prenom" value="${param.prenom }" size="20" maxlength="20" />
                    <span class="erreur">${erreurs['prenom']}</span>
                    
                    <br />
    				
    				
                    <label for="dept">Departement</label>
                    <input type="text" class="form-control" id="dept" name="dept" value="${param.dept }" size="20" maxlength="20" />
                    
                    <br />
    
    				
                    <label for="poste">Poste</label>
                    <input type="text" class="form-control" id="poste" name="poste" value="${param.poste }" size="20" maxlength="20" />
                    
                    <br />
                    
                    
                    
                    <label for="email">Adresse email <span class="requis">*</span></label>
                	<input type="email" class="form-control" id="email" name="email" value="${param.email}" size="20" maxlength="60" />
                	<span class="erreur">${erreurs['email']}</span>
                	<br />
                	
                	
                    
                  	<label for="agence">Agence <span class="requis">*</span></label>
                    <input type="text" class="form-control" id="agence" name="agence" value="${param.agence }" size="20" maxlength="60" />
                    <br />
                    
                    
                    
                    <label for="mdp">Mot de passe <span class="requis">*</span></label>
               	 	<input type="password" class="form-control" id="mdp" name="mdp" value="" size="20" maxlength="20" />
                	<span class="erreur">${erreurs['mdp']}</span>
                	<br />
                    
                    <label for="confirmation">Confirmation du mot de passe <span class="requis">*</span></label>
                	<input type="password" class="form-control" id="confirmation" name="confirmation" value="" size="20" maxlength="20" />
                	<span class="erreur">${erreurs['confirmation']}</span>
                	<br />
                    <br>
                    
      				<label for="select">Type : </label>
      				<select id="select" class="form-control" name="type">
        				<option value="Employe">Employe</option>
        				<option value="Technicien">Technicien</option>
        				<option value="Technicien-Responsable">Technicien-Responsable</option>
        				<option value="Admin">Admin</option>
      				</select>
    				</div>
                </fieldset>
                <input type="submit" value="Valider"  />
                <input type="reset" value="Remettre à zéro" /> <br />
            </form>
        </div>

</div>
</body>
</html>