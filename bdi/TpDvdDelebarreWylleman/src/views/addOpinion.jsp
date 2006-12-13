<form method="POST" action="ControllerAddOpinion"> 
	<% 
	String dvdId = request.getParameter("dvdId"); 
	dao.core.DAOFactory factory = (dao.core.DAOFactory) application.getAttribute("factory");
	dao.business.DVDDAO dvdDAO = factory.getDVDDAO();
	models.DVD dvd = dvdDAO.getDVDById(Integer.parseInt(dvdId));
	%>
	<h3>Commentez le film <i><%= dvd.getTitle() %></i></h3>
	
	<input name="dvdId" type="hidden" value="<%= dvdId %>">
	<p><textarea name="text" cols="40" rows="6"></textarea></p>
	
	<p> Cliquez sur <input type="submit" value="Valider"/> pour soumettre votre commentaire, sinon <input type="reset" value="Annuler"/> </p>
</form>