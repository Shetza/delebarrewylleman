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

public class AddOpinion extends Controller {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// Impossible d'ajouter un commentaire sur un DVD par la methode GET !!!
		// On transmet sans rien faire.
		super.forward("", request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			User user = (User) context.getAttribute("user");
			
			String tempId = request.getParameter("dvdId");
			String text = request.getParameter("text");
			
			if (tempId == null || tempId.equals("")) {
				String error = "Ajout de commentaire annul&eacute;, l'identifiant du DVD n'est pas correct.";
				super.forward("", "error", error, request, response);
			}
			else if (text == null || text.equals("")) {
				String error = "Ajout de commentaire annul&eacute;, le commentaire est vide.";
				super.forward("addOpinion&dvdId="+tempId, "error", error, request, response);
			}
			else {
				//date = Date.valueOf(tempDate);
				int dvdId = Integer.parseInt(tempId);
				
				OpinionDAO opinionDAO = factory.getOpinionDAO();
				Opinion opinion = opinionDAO.create(dvdId, user.getId());
				
				opinion.setText(super.protectString(text));
				opinionDAO.update(opinion);
				
				String message = "Votre commentaire a bien &eacute;t&eacute; enregistr&eacute;.";
				super.forward("details&dvdId="+tempId, "message", message, request, response);
			}
		}
		catch(Exception e) {
			String error = "Le Commentaire n'a pas pu &ecirc;tre enregistr&eacute;, une erreur est survenue: " + e.toString();
			super.forward("", "error", error, request, response);
		}
	}
}

