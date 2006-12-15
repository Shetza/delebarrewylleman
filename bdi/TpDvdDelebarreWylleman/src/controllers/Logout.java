package controllers;

import dao.core.*;

import models.User;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

public class Logout extends Controller {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			if ( user == null ) {
				String error = "Fermeture de session annul&eacute;e, auncune session ouverte.";
				super.forward("logout", "error", error, request, response);
			}
			else {
				String message = "A bient&ocirc;t " + user.getFirstName() + " " + user.getLastName() + ".";
				session.removeAttribute("user");
				super.forward("logout", "message", message, request, response);
			}
		}
		catch(Exception e) {
			String error = "Fermeture de session annul&eacute;e, une erreur est survenue: " + e.toString();
			super.forward("logout", "error", error, request, response);
		}
	}

}


