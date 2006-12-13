<form> 
	<% 
	models.User user = (models.User) application.getAttribute("user");
	String dvdId = request.getParameter("dvdId"); 
	dao.core.DAOFactory factory = (dao.core.DAOFactory) application.getAttribute("factory");
	dao.business.DVDDAO dvdDAO = factory.getDVDDAO();
	dao.business.KindDAO kindDAO = factory.getKindDAO();
	models.DVD dvd = dvdDAO.getDVDById(Integer.parseInt(dvdId));
	models.Kind kind = kindDAO.getKindById(dvd.getKind());
	%>
	<h3><%= dvd.getTitle() %></h3>
	<p>Cat&eacute;gorie: <%= kind.getName() %></p>
	<p>Date de parution: <%= dvd.getDate().toString() %></p>
	<p>Artistes: 
	<% 
	java.util.List artists = dvdDAO.getArtists(dvd);
	if ( artists != null ) {
	%>
	<table>
		<thead>
			<td>Nom</td>
			<td>Fonction</td>
		</thead>
		<tbody>
		<% 
		for ( java.util.Iterator it = artists.iterator(); it.hasNext(); ) {
			models.Artist currentArtist = (models.Artist) it.next();
			if ( currentArtist != null ) {
			%>
			<tr>
				<td><%= currentArtist.getName() %></td>
				<td><%= currentArtist.getFunction() %></td>
			</tr>
			<% 
			}
		}
		%>
		</tbody>
	</table>
	</p>
	<p>
	<% 
	}
	dao.business.OpinionDAO opinionDAO = factory.getOpinionDAO();
	// Si l'utilisateur courant a deja ajouter un commentaire pour ce DVD.
	models.Opinion opinion = opinionDAO.getOpinionById(dvd.getId(), user.getId());
	if ( opinion != null ) {
		// Alors il peut editer ce commentaire.
		application.setAttribute("opinion", opinion);
	%>
	<a href="view.jsp?action=modifyOpinion&dvdId=<%= dvdId %>">Modifier votre commentaire</a>
	<a href="ControllerDeleteOpinion?dvdId=<%= dvdId %>" onclick="return confirm('&Ecirc;tes-vous s&ucirc;r de vouloir supprimer votre commentaire ?')">Supprimer votre commentaire</a>
	<% 
	}
	else {
	%>
	<a href="view.jsp?action=addOpinion&dvdId=<%= dvdId %>">Ajouter un commentaire</a>
	<% 
	}
	dao.business.UserDAO userDAO = factory.getUserDAO();
	java.util.List opinions = opinionDAO.search(dvd.getId(), null);
	if ( opinions != null ) {
		for ( java.util.Iterator it = opinions.iterator(); it.hasNext(); ) {
			models.Opinion currentOpinion = (models.Opinion) it.next();
			if ( currentOpinion != null ) {
			%>
			<hr/>
			Commentaire de <b><%= userDAO.getUserById( currentOpinion.getAuthorId() ) %></b><br/>
			<%= currentOpinion.getText() %>
			<hr/>
			<% 
			}
		}
	}
	%>
	</p>
</form>