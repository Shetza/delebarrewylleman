<form method="POST" action="ControllerModifyOpinion"> 
	<% 
	String dvdId = request.getParameter("dvdId"); 
	dao.core.DAOFactory factory = (dao.core.DAOFactory) application.getAttribute("factory");
	dao.business.DVDDAO dvdDAO = factory.getDVDDAO();
	models.DVD dvd = dvdDAO.getDVDById(Integer.parseInt(dvdId));
	%>
	<h3>Modifiez votre commentaire sur le film <i><%= dvd.getTitle() %></i></h3>
	
	<input name="dvdId" type="hidden" value="<%= dvdId %>">
	<% 
	models.Opinion opinion = (models.Opinion) application.getAttribute("opinion");
	%>
	<p><textarea name="text" cols="40" rows="6"><%= opinion.getText() %></textarea></p>
	
	<p> Cliquez sur <input type="submit" value="Valider"/> pour soumettre votre commentaire, sinon <input type="reset" value="Annuler"/> </p>
</form>