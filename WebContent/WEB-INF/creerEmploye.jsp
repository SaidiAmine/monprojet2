<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>Création d'un employé</title>
<link type="text/css" rel="stylesheet" href="inc/style.css" />
</head>
<body>
<c:import url="/inc/menuadmin.jsp" />
<%-- <c:if test="${masession.type=='Admin'}"></c:if>--%>
	 <div>
            <form method="post" action="creeremploye">
            <%--^ Les données seront envoyés a la servlet CreationEmploye  --%>
                <fieldset>
                	<p>${message}</p>
                    <legend>Informations employé</legend>
    
                    <label for="nom">Nom d'utilisateur</label>
                	<input type="text" id="nom" name="nom" value="${param.nom}" size="20" maxlength="20" />
                	<span class="erreur">${erreurs['nom']}</span>
                	<br />
                    
                    <label for="prenom">Prénom <span class="requis">*</span></label>
                    <input type="text" id="prenom" name="prenom" value="${param.prenom }" size="20" maxlength="20" />
                    <span class="erreur">${erreurs['prenom']}</span>
                    <br />
    
                    <label for="dept">Departement</label>
                    <input type="text" id="dept" name="dept" value="${param.dept }" size="20" maxlength="20" />
                    <br />
    
                    <label for="poste">Poste</label>
                    <input type="text" id="poste" name="poste" value="${param.poste }" size="20" maxlength="20" />
                    <br />
                    
                    <label for="email">Adresse email <span class="requis">*</span></label>
                	<input type="email" id="email" name="email" value="${param.email}" size="20" maxlength="60" />
                	<span class="erreur">${erreurs['email']}</span>
                	<br />
                    
                  	<label for="agence">Agence <span class="requis">*</span></label>
                    <input type="text" id="agence" name="agence" value="${param.agence }" size="20" maxlength="60" />
                    <br />
                    
                    <label for="mdp">Mot de passe <span class="requis">*</span></label>
               	 	<input type="password" id="mdp" name="mdp" value="" size="20" maxlength="20" />
                	<span class="erreur">${erreurs['mdp']}</span>
                	<br />
                    
                    <label for="confirmation">Confirmation du mot de passe <span class="requis">*</span></label>
                	<input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" />
                	<span class="erreur">${erreurs['confirmation']}</span>
                	<br />
                    <br>
                    
                    
                    <span class="info">Role:</span><br>
                    <input type="radio" name="type" value="Admin" id="r2"> Admin<br>
                    <input type="radio" name="type" value="Employe" id="r2"> Employe<br>
                    <input type="radio" name="type" value="Technicien" id="r1"> Technicien<br>
                    <input type="radio" name="type" value="Technicien-Responsable" id="r1"> Technicien-Responsable<br>
                	<span class="erreur">${erreurs['type']}</span>
                </fieldset>
                <input type="submit" value="Valider"  />
                <input type="reset" value="Remettre à zéro" /> <br />
            </form>
        </div>
</body>
</html>