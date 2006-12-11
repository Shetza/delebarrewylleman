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
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.search(request, response, null, "-1", null);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String title = request.getParameter("title");
		String tempKind = request.getParameter("kind");
		String date = request.getParameter("date");
		
		this.search(request, response, title, tempKind, date);
	}
	
	private void search(HttpServletRequest request, HttpServletResponse response, String title, String tempKind, String date) throws IOException, ServletException {
		//String order = request.getParameter("order");
		String source = request.getParameter("source");
		String tempUser = request.getParameter("user");
		try {			
			int kindId = -1;
			if (tempKind != null && !tempKind.equals("") ) kindId = Integer.parseInt(tempKind);
			int userId = -1;
			if (tempUser != null && !tempUser.equals("") ) userId = Integer.parseInt(tempUser);
			
			DVDDAO dvdDAO = factory.getDVDDAO();
			List dvds = dvdDAO.search(userId, title, kindId, date, null);
			
			super.forward(source, "message", dvds, request, response);
		}
		catch(Exception e) {
			String error = "Impossible de poursuivre la recherche, une erreur est survenue: " + e.toString();
			super.forward(source, "error", error, request, response);
		}
	}
	
}

