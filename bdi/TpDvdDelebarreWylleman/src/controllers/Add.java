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
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

public class Add extends Controller {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// Impossible d'ajouter un DVD par la methode GET !!!
		// On transmet sans rien faire.
		super.forward("", request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int kindId = 0;
		String title = null;
		Date date = null;
		
		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			
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
				
				DVDDAO dvdDAO = factory.getDVDDAO();
				DVD dvd = (DVD) dvdDAO.create();
				
				KindDAO kindDAO = factory.getKindDAO();
				Kind kind = kindDAO.getKindById(kindId);
				
				dvd.setTitle(title);
				dvd.setKind(kindId);
				dvd.setDate(date);
				dvd.setUser(user.getId());
				
				dvdDAO.update(dvd);
				
				String message = "Le DVD <b>" + title + "</b> paru le <u>" + date + "</u> a bien &eacute;t&eacute; ajout&eacute; dans la cat&eacute;gorie <i>" + kind.getName() + "</i>.";
				super.forward("", "message", message, request, response);
			}
		}
		catch(Exception e) {
			String error = "Le DVD n'a pas pu &ecirc;tre ajout&eacute;, une erreur est survenue: " + e.toString();
			super.forward("add", "error", error, request, response);
		}
	}
}

