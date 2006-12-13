package controllers;

import dao.core.*;
import dao.business.OpinionDAO;

import models.Opinion;
import models.User;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class DeleteOpinion extends Controller {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			User user = (User) context.getAttribute("user");
			
			String tempId = request.getParameter("dvdId");
			
			if (tempId == null || tempId.equals("")) {
				String error = "Suppression de commentaire annul&eacute;e, l'identifiant du DVD n'est pas correct.";
				super.forward("", "error", error, request, response);
			}
			else {
				int dvdId = Integer.parseInt(tempId);
				
				OpinionDAO opinionDAO = factory.getOpinionDAO();
				Opinion opinion = opinionDAO.getOpinionById(dvdId, user.getId());
				opinionDAO.delete(opinion);
				
				String message = "Votre commentaire a bien &eacute;t&eacute; supprim&eacute;.";
				super.forward("details&dvdId="+tempId, "message", message, request, response);
			}
		}
		catch(Exception e) {
			String error = "Le Commentaire n'a pas pu &ecirc;tre supprim&eacute;, une erreur est survenue: " + e.toString();
			super.forward("", "error", error, request, response);
		}
	}
}

