<form method="POST" action="ControllerSearch"> 
	<% 
	java.util.List kinds = (java.util.List) application.getAttribute("kinds");
	models.User user = (models.User) session.getAttribute("user");
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
		java.util.List dvds = (java.util.List) request.getAttribute("dvds");
		request.removeAttribute("dvds");
		if ( dvds == null ) {
		}
		else if ( dvds.isEmpty() ) {
			out.write("</table>\n<ul>\nAucun dvd n'a &eacute;t&eacute; trouv&eacute;.\n</ul>\n");
		}
		else {
			%>
		<tbody style="max-height: 320px; overflow: auto;">
			<% 
			dao.core.DAOFactory factory = (dao.core.DAOFactory) application.getAttribute("factory");
			dao.business.KindDAO kindDAO = factory.getKindDAO();
			dao.business.LoanDAO loanDAO = factory.getLoanDAO();
			
			for ( java.util.Iterator it = dvds.iterator(); it.hasNext(); ) {
				models.DVD currentDVD = (models.DVD) it.next();
				models.Kind currentKind = kindDAO.getKindById(currentDVD.getKind());
				%>
		<tr>
			<td style="text-align:left;"><%= currentDVD.getTitle() %></td>
			<td style="text-align:left;"><%= currentKind.getName() %></td>
			<td style="text-align:left;"><%= currentDVD.getDate().toString() %></td>
			<td style="text-align:left;">
				<% 
				// Si le DVD est actuellement disponible a l'emprunt
				// et si l'utilisateur peut encore emprunter des DVDs.
				if ( loanDAO.isBorrowable(currentDVD) && loanDAO.canBorrow(user) ) {
				%>
				<a href="ControllerBorrow?dvdId=<%= currentDVD.getId() %>" onclick="return confirm('&Ecirc;tes-vous s&ucirc;r de vouloir emprunter le DVD <%= currentDVD.getTitle() %> ?')">emprunter</a>
				<% 
				}
				// Si le DVD est actuellement disponible a la reserve
				// et si l'utilisateur peut encore reserver des DVDs
				// et si l'utilisateur peut reserver ce DVD (s'il ne l'a pas emprunte).
				if ( loanDAO.isReservable(currentDVD) && loanDAO.canReserve(user) && loanDAO.canReserve(currentDVD, user) ) {
				%>
				<a href="ControllerReserve?dvdId=<%= currentDVD.getId() %>" onclick="return confirm('&Ecirc;tes-vous s&ucirc;r de vouloir r&eacute;server le DVD <%= currentDVD.getTitle() %> ?')">r&eacute;server</a>
				<% 
				}
				%>
				<a href="view.jsp?action=details&dvdId=<%= currentDVD.getId() %>">D&eacute;tails</a>
			</td>
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

<form>
	<ul>
		<li>Vous ne pouvez pas emprunter un DVD? Il est surement d&eacute;ja emprunt&eacute; par un autre utilisateur ou vous l'avez d&eacute;ja emprunt&eacute; aujourd'hui.</li>
		<li>Vous ne pouvez pas r&eacute;server un DVD? Il est surement d&eacute;ja r&eacute;serv&eacute; par un autre utilisateur ou vous l'avez actuellement emprunt&eacute;.</li>
		<li>Vous ne pouvez emprunter <b>aucun</b> DVD? Vous devez surement avoir atteint votre limite d'emprunt de <b>3</b> DVDs.</li>
		<li>Vous ne pouvez r&eacute;server <b>aucun</b> DVD? Vous devez surement avoir atteint votre limite de r&eacute;serve de <b>3</b> DVDs.</li>
	</ul>
</form>