<% 
dao.core.DAOFactory factory = (dao.core.DAOFactory) application.getAttribute("factory");
models.User user = (models.User) session.getAttribute("user");
dao.business.LoanDAO loanDAO = factory.getLoanDAO();
java.util.Date currentDate = new java.util.Date(System.currentTimeMillis());
%>
<form> 
	<h3>Mes Emprunts</h3>
	<br/>
	<table style="width: 100%;">
		<thead style="text-align: left;">
			<tr>
				<td>DVD</td>
				<td>Date d'emprunt</td>
				<td>Date limite de retour</td>
				<td>Actions possibles</td>
			</tr>
		</thead>
		<% 
		java.util.List emprunts = loanDAO.getLoans(user);
		
		if ( emprunts == null ) {
		}
		else if ( emprunts.isEmpty() ) {
			out.write("</table>\n<ul>\nAucun dvd n'a &eacute;t&eacute; emprunt&eacute;.\n</ul>\n");
		}
		else {
			%>
		<tbody>
			<% 			
			for ( java.util.Iterator it = emprunts.iterator(); it.hasNext(); ) {
				models.Loan currentLoan = (models.Loan) it.next();
				%>
		<tr>
			<td style="text-align:left;"><% out.write(currentLoan.getDVDTitle()); %></td>
			<td style="text-align:left;"><% out.write(currentLoan.getBegin().toString()); %></td>
			<td style="text-align:left;"><% 
				java.util.Date limit = currentLoan.getLimit();
				if ( currentDate.compareTo(limit) > 0 ) {
					out.write("<div id=error>" + limit.toString() + "</div>");
				}
				else out.write(limit.toString());
			%></td>
			<td style="text-align:left;">
				<a href="ControllerGiveBack?dvdId=<%= currentLoan.getDVDId() %>" onclick="return confirm('&Ecirc;tes-vous s&ucirc;r de vouloir rendre le DVD <%= currentLoan.getDVDTitle() %> ?')"><img hspace="2" width="16" height="16" src="./themes/original/img/b_drop.png" alt="Rendre ce DVD" title="Rendre cd DVD" border="0" /></a>
				<a href="view.jsp?action=details&dvdId=<%= currentLoan.getDVDId() %>">D&eacute;tails</a>
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
	<h3>Mes R&eacute;servations</h3>
	<br/>
	<table style="width: 100%;">
		<thead style="text-align: left;">
			<tr>
				<td>DVD</td>
				<td>Date th&eacute;orique d'emprunt</td>
				<td>Actions possibles</td>
			</tr>
		</thead>
		<% 
		java.util.List reserves = loanDAO.getReserves(user);
		
		if ( reserves == null ) {
		}
		else if ( reserves.isEmpty() ) {
			out.write("</table>\n<ul>\nAucun dvd n'a &eacute;t&eacute; r&eacute;serv&eacute;.\n</ul>\n");
		}
		else {
			%>
		<tbody>
			<% 
			for ( java.util.Iterator it = reserves.iterator(); it.hasNext(); ) {
				models.Loan currentLoan = (models.Loan) it.next();
				%>
		<tr>
			<td style="text-align:left;"><% out.write(currentLoan.getDVDTitle()); %></td>
			<td style="text-align:left;"><% 
				java.util.Date limit = currentLoan.getLimit();
				if ( currentDate.compareTo(limit) >= 0 ) {
					out.write("<div id=validator>" + limit.toString() + "</div>");
				}
				else out.write(limit.toString());
			%></td>
			<td style="text-align:left;">
				<a href="ControllerCancelReserve?dvdId=<%= currentLoan.getDVDId() %>" onclick="return confirm('&Ecirc;tes-vous s&ucirc;r de vouloir annuler la r&eacute;servation du DVD <%= currentLoan.getDVDTitle() %> ?')"><img hspace="2" width="16" height="16" src="./themes/original/img/b_drop.png" alt="Annuler la r&eacute;servation de ce DVD" title="nnuler la r&eacute;servation de ce DVD" border="0" /></a>
				<a href="view.jsp?action=details&dvdId=<%= currentLoan.getDVDId() %>">D&eacute;tails</a>
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
		<p>Une date en <class style="color:#FF0000; font-weight:bold;">rouge</class> signifie que vous avez d&eacute;pass&eacute; la date &agrave; laquelle vous deviez rendre le DVD.</p>
		<p>Une date en <class style="color:#00FF00; font-weight:bold;">vert</class> signifie que le DVD que vous avez r&eacute;serv&eacute; doit &ecirc;tre normalement rendu aujourd'hui.</p>
	</ul>
</form>