<form method="POST" action="ControllerSearch"> 
	<% 
	models.User user = (models.User) application.getAttribute("user");
	java.util.List kinds = (java.util.List) application.getAttribute("kinds");
	%>
	<h3>Mes DVDs</h3>
	<table style="width: 100%;">
		<thead style="text-align: left;">
			<tr>
				<td>Titre</td>
				<td>Cat&eacute;gorie</td>
				<td>Date de parution<br/>(yyyy-mm-dd)</td>
				<td>Actions possibles</td>
			</tr>
		</thead>
		<% 
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