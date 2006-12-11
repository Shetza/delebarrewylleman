<form method="POST" action="ControllerSearch"> 
	<% 
	java.util.List kinds = (java.util.List) application.getAttribute("kinds");
	%>
	<h3>Rechercher un DVD</h3>
	<table style="width: 100%;">
		<thead style="text-align: left;">
			<tr>
				<td>Titre</td>
				<td>Cat&eacute;gorie</td>
				<td>Date de parution<br/>(yyyy-mm-dd)</td>
				<td>Actions possibles</td>
			</tr>
			<tr>
				<td>
					<input name="title" type="text" size="30" 
					<% 
					String title = request.getParameter("title");
					if ( title != null ) {
						out.write("value=\"" + title + "\"");
					}
					%>>
				</td>
				<td>
					<select name="kind" size="1">
					<% String kind = request.getParameter("kind"); %>
						<option value="" selected="selected"> - Choisir - </option>
						<%
						for ( java.util.Iterator it = kinds.iterator(); it.hasNext(); ) {
							models.Kind currentKind = (models.Kind) it.next();
							if ( currentKind != null ) {
								out.write("<option");
								if ( kind != null && kind.equals(currentKind.getId()+"") ) out.write(" selected=\"selected\"");
								out.write(" value=\"" + currentKind.getId() + "\">" + currentKind.getName() + "</option>");
							}
						}
						%>
					</select>
				</td>
				<td>
					<input name="date" type="text" size="10" 
					<% 
					String date = request.getParameter("date");
					if ( date != null ) {
						out.write("value=\"" + date + "\"");
					}
					%>>
				</td>
				<td>
					<input value="Affiner la rechercher" type="submit">
				</td>
			</tr>
		</thead>
		<% 
		// AU LIEU DE METTRE LE NOM DES COLONNES A LA MAIN
		// LES ECRIRES EN TEMPS QUE 1ERE LIGNE DE LA LISTE DE DVDS.
		// AINSI ON POURRAI AJOUTER DES COLONNES SUIVANT LA REQUETE
		// SANS CHANGER CETTE VUE...
		java.util.List dvds = (java.util.List) request.getAttribute("message");
		request.removeAttribute("message");
		if ( dvds.isEmpty() ) {
			out.write("</table>\n<ul>\nAucun dvd n'a &eacute;t&eacute; trouv&eacute;.\n</ul>\n");
		}
		else {
			%>
		<tbody style="max-height: 320px; overflow: auto;">
			<% 
			dao.core.DAOFactory factory = (dao.core.DAOFactory) application.getAttribute("factory");
			dao.business.KindDAO kindDAO = factory.getKindDAO();
			
			for ( java.util.Iterator it = dvds.iterator(); it.hasNext(); ) {
				models.DVD currentDVD = (models.DVD) it.next();
				models.Kind currentKind = (models.Kind) kindDAO.getModelById(currentDVD.getKind());
				%>
		<tr>
			<td style="text-align:left;"><% out.write(currentDVD.getTitle()); %></td>
			<td style="text-align:left;"><% out.write(currentKind.getName()); %></td>
			<td style="text-align:left;"><% out.write(currentDVD.getDate().toString()); %></td>
			<td style="text-align:left;">Reserver/Emprunter/Rendre/Prolonger/Details</td>
		</tr>
				<% 
			}
			%>
		<tbody>
	</table>
			<% 
		}
		%>
</form>