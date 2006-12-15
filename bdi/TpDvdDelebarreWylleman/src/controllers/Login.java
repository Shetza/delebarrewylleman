package controllers;

import dao.core.*;
import dao.business.UserDAO;

import models.User;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

public class Login extends Controller {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// Impossible de s'identifier par la methode GET !!!
		// On transmet sans rien faire.
		super.forward("", request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String login = null;
		String pass = null;
		
		try {
			login = (String) request.getParameter("login");
			pass = (String) request.getParameter("pass");
			
			if (login == null || login.equals("")) {
				String error = "Identification annul&eacute;e, le login n'est pas correct.";
				super.forward("login", "error", error, request, response);
			}
			else if (pass == null || pass.equals("")) {
				String error = "Identification annul&eacute;e, le mot de passe n'est pas correct.";
				super.forward("login", "error", error, request, response);
			}
			else {
				UserDAO userDAO = factory.getUserDAO();
				User user = userDAO.getUserByLogin(login);
				
				if ( user == null || !user.getPassword().equals(pass) ) {
					String error = "Identification annul&eacute;e, le nom d'utilisateur n'existe pas ou il ne correspond pas au mot de passe.";
					super.forward("login", "error", error, request, response);
				}
				else {
					HttpSession session = request.getSession();
					session.setAttribute("user", user);
					String message = "Bienvenue " + user.getFirstName() + " " + user.getLastName() + " sur le service de partage de DVDs.";
					super.forward("myDVDs", "message", message, request, response);
				}
			}
		}
		catch(Exception e) {
			String error = "Identification annul&eacute;e, une erreur est survenue: " + e.toString();
			super.forward("login", "error", error, request, response);
		}
	}

}


