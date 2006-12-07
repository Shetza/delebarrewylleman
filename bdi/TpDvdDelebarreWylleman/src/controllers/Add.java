package controllers;

import dao.core.*;
import dao.business.DVDDAO;
import dao.business.KindDAO;

import models.DVD;
import models.Kind;
import models.User;

import java.sql.Date;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class Add extends Controller {
	
	/**
	 * La fabrique de DAO.
	 */
	private DAOFactory factory;
	
	/**
	 * L'utilisateur de la session en cours.
	 */
	private User user;
	
	public void init(ServletConfig config) throws ServletException {
		try {
			ServletContext context = config.getServletContext();
			factory = (DAOFactory) context.getAttribute("factory");
			user = (User) context.getAttribute("user");
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
    
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int kindId = -1;
		String title = null;
		Date date = null;
		
		try {
			title = request.getParameter("title");
			String tempKind = request.getParameter("kind");
			String tempDate = request.getParameter("date");
			
			if (title == null || title.equals("")) {
				String error = "Ajout de DVD annul&eacute;, le titre du DVD n'est pas correct.";
				super.forward("add", "error", error, request, response);
			}
			else if (tempKind == null || tempKind.equals("") ) {
				String error = "Ajout de DVD annul&eacute;, la cat&eacute;gorie du DVD n'est pas correcte.";
				super.forward("add", "error", error, request, response);
			}
			else if (tempDate == null || tempDate.equals("")) {
				String error = "Ajout de DVD annul&eacute;, la date du DVD n'est pas correct.";
				super.forward("add", "error", error, request, response);
			}
			else {
				date = Date.valueOf(tempDate);
				kindId = Integer.parseInt(tempKind);
				
				DVDDAO dvdDAO = (DVDDAO) factory.getDAO("dvd");
				DVD dvd = (DVD) dvdDAO.create();
				
				KindDAO kindDAO = (KindDAO) factory.getDAO("kind");
				Kind kind = (Kind) kindDAO.getModelById(kindId);
				
				dvd.setTitle(title);
				dvd.setKind(kindId);
				dvd.setDate(date);
				dvd.setUser(user.getId());
				
				dvdDAO.update(dvd);
				
				String message = "Le DVD " + title + " paru le " + date + " a bien &eacute;t&eacute; ajout&eacute; sous la cat&eacute;gorie " + kind.getName() + ".";
				super.forward("", "message", message, request, response);
			}
		}
		catch(Exception e) {
			String error = "Le DVD n'a pas pu &ecirc;tre ajout&eacute;, une erreur est survenue: " + e.toString();
			super.forward("add", "error", error, request, response);
		}
	}
}

