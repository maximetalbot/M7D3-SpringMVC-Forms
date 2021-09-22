<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ajout JSP</title>
<style type="text/css">
	label{
	display: inline-block;
	width: 100px;
	}
</style>
</head>
<body>
	<h1>Ajout d'une personne</h1>
	<div>
		<form:form action="ajout" method="POST">
			<div>
				<form:label path="prenom">Prénom: </form:label>
				<form:input path="prenom"/>
			</div>
			<div>
				<form:label path="nom">Nom: </form:label>
				<form:input path="nom"/>
			</div>
			<div>
				<form:label path="adresse.codePostal">Code Postal: </form:label>
				<form:input path="adresse.codePostal"/>
			</div>
			<div>
				<form:label path="adresse.ville">Ville: </form:label>
				<form:input path="adresse.ville"/>
			</div>
			<div>
				<input type="submit" value="Ajouter"/>
			</div>
		</form:form>
	</div>
	<p>
		Date et heure: ${heure}
	</p>
</body>
</html>