package controllers;

import dao.core.*;
import dao.business.DVDDAO;

import models.User;

import java.util.List;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class Search extends Controller {
	
	/**
	 * La fabrique de DAO.
	 */
	private DAOFactory factory;
		
	/**
	 * L'utilisateur de la session en cours.
	 */
	private User user;
	
	public void init(ServletConfig config) throws ServletException {
		try{
			ServletContext context = config.getServletContext();
			factory = (DAOFactory) context.getAttribute("factory");
			user = (User) context.getAttribute("user");
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
    
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.search(request, response, null, -1, null);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int kindId = -1;
		String title = null;
		String date = null;
		
		title = request.getParameter("title");
		String tempKind = request.getParameter("kind");
		date = request.getParameter("date");
		
		kindId = Integer.parseInt(tempKind);
		
		this.search(request, response, title, kindId, date);
	}
	
	private void search(HttpServletRequest request, HttpServletResponse response, String title, int kindId, String date) throws IOException, ServletException {
		try {			
			DVDDAO dvdDAO = (DVDDAO) factory.getDAO("dvd");
			List dvds = dvdDAO.search(title, kindId, date);
			
			super.forward("search", "message", dvds, request, response);
		}
		catch(Exception e) {
			String error = "Impossible de poursuivre la recherche, une erreur est survenue: " + e.toString();
			super.forward("search", "error", error, request, response);
		}
	}
	
}

