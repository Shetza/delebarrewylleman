<form method="POST" action="ControllerAdd"> 
	<h3>Ajouter un DVD</h3>
	<br/>
	<% 
	java.util.List kinds = (java.util.List) application.getAttribute("kinds");
	%>
	<table style="width: 100%;" style="text-align: left;">
		<tr>
			<td>Titre</td>
			<td>Cat&eacute;gorie</td>
			<td>Date de parution (yyyy-mm-dd)</td>
		</tr>
		<tr>
			<td>
				<input name="title" type="text" size="30">
			</td>
			<td>
				<select name="kind" size="1">
					<option value="" selected="selected"> - Choisir - </option>
					<%
					for ( java.util.Iterator it = kinds.iterator(); it.hasNext(); ) {
						models.Kind currentKind = (models.Kind) it.next();
						if ( currentKind != null ) {
							out.write("<option value=\"" + currentKind.getId() + "\">" + currentKind.getName() + "</option>");
						}
					}
					%>
				</select>
			</td>
			<td>
				<input name="date" type="text" size="10">
			</td>
		</tr>
	</table>

	<p> Cliquez sur <input type="submit" value="Valider"/> pour soumettre votre requ&ecirc;te, sinon <input type="reset" value="Annuler"/> </p>
</form>