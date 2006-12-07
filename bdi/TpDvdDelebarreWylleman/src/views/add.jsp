<% 
try {
	java.util.List kinds = (java.util.List) application.getAttribute("kinds");
	if ( kinds == null ) {
		request.setAttribute("error", new String("Probl&egrave;me lors de le r&eacute;cuperation de la liste des cat&eacute;gories."));
		%>
		<jsp:include page="/error.jsp" flush="true" />
		<%
	}
	else {
		%>
<form method="POST" action="ControllerAdd"> 
	<h3>Ajouter un DVD</h3>
	
	<p> Titre : <input name=title type=text> </p>
	<p> Cat&eacute;gorie : 
		<select name="kind" size="1">
			<option selected value=""> - Choisir - </option>
		<%
		for ( java.util.Iterator it = kinds.iterator(); it.hasNext(); ) {
			models.Kind currentKind = (models.Kind) it.next();
			out.println("<option value=" + currentKind.getId() + ">" + currentKind.getName() + "</option>");
		}
	%>
		</select>
	</p>
	<p> Date : <input name=date type=text> (format yyyy-mm-dd) </p>
	
	<p> Cliquez sur <input type="submit" value="Valider"/> pour soumettre votre requ&ecirc;te, sinon <input type="reset" value="Annuler"/> </p>
</form>
	<%
	}
} catch(Exception e) {
	out.println("erreur: "+e.toString());
}
%>