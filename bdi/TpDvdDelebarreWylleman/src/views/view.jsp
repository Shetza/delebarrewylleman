<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
	<meta name="robots" content="all" />
	<meta name="author" content="Johann DELEBARRE" />
	<meta name="author" content="Julien WYLLEMAN" />
	<meta name="keywords" content="BDD" />
	<meta name="description" content="Service de partage de DVDs" />
	<title>TP Projet BDD - Delebarre Wylleman</title>
	<link rel="stylesheet" type="text/css" href="style.css" />
</head>

<% 
	models.User user = (models.User) application.getAttribute("user");
%>

<body>
	<div id="header">
		<h1>Service de partage de DVDs</h1>
	</div>
	
	<div id="main">
		<div id="sidebar">
			<h2>Menu</h2>
			<ul>
			<% 
				// L'utilisateur doit �tre identifi� pour voir le menu.
				if ( user == null ) {
				%>
				<li><a href="view.jsp?action=login"><b>Se connecter</b></a></li>
				<% 
				}
				else {
				%>
				<li><a href="ControllerLogout"><b>Se d&eacute;connecter</b></a></li>
				<br>
				<li><a href="ControllerPrepareKinds?source=add">Ajouter un DVD</a></li>
				<li><a href="view.jsp?action=remove">Supprimer un DVD</a></li>
				<li><a href="ControllerPrepareKinds?source=search">Rechercher un DVD</a></li>
				<br>
				<li><a href="view.jsp?action=tools">Outils</a></li>
				<br>
				<li><a href="Manual.html">Consulter l'aide</a></li>
				<% 
				}
			%>
			</ul>
		</div>
	</div>
	
	<div id="content">
	
	<% 
	String action = request.getParameter("action");
	if ( action == null ) {
		request.removeAttribute("action");
		request.setAttribute("message", new String("Bienvenue sur le service de partage de DVDs de Johann DELEBARRE et Julien WYLLEMAN.<br>\nMerci de vous identifier."));
	}
	else {
		if ( user == null && !action.equals("login") && !action.equals("logout") ) {
			request.setAttribute("error", new String("Acc&egrave;s restreint, veuillez vous identifier."));
		}
		else {
			String dvd = request.getParameter("dvd");
			String filename = "/" + action + ".jsp";
			if ( dvd != null )	filename += "?dvd=" + dvd;
			%>
				<jsp:include page="<%= filename.toString() %>" flush="true" />
			<%
		}
	}
	%>
	<jsp:include page="/error.jsp" flush="true" />
	<jsp:include page="/message.jsp" flush="true" />
	
	</div>
	
	<div id="footer">(c) 2006 - J. Delebarre, J. Wylleman</div>
</body>

</html>