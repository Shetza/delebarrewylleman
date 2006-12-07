package controllers;

import dao.core.*;

import models.User;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class Logout extends Controller {
	
	/**
	 * Le contexte de la <code>Servlet</code>/
	 */
	private ServletContext context;
	
	public void init(ServletConfig config) throws ServletException {
		try{
			context = config.getServletContext();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			User user = (User) context.getAttribute("user");
			if ( user == null ) {
				String error = "Fermeture de session annul&eacute;e, auncune session ouverte.";
				super.forward("logout", "error", error, request, response);
			}
			else {
				String message = "A bient&ocirc;t " + user.getFirstName() + " " + user.getLastName() + ".";
				context.removeAttribute("user");
				super.forward("logout", "message", message, request, response);
			}
		}
		catch(Exception e) {
			String error = "Fermeture de session annul&eacute;e, une erreur est survenue: " + e.toString();
			super.forward("logout", "error", error, request, response);
		}
	}

}


