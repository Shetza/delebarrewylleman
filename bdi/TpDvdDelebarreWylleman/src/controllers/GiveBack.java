package controllers;

import dao.core.*;
import dao.business.LoanDAO;

import models.Loan;
import models.User;

import java.util.Date;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class GiveBack extends Controller {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			User user = (User) context.getAttribute("user");
			Date date = new Date(System.currentTimeMillis());
			String tempDvd = request.getParameter("dvdId");
			
			if (tempDvd == null || tempDvd.equals("")) {
				String error = "Retour de DVD annul&eacute;, le DVD n'est pas correct.";
				super.forward("", "error", error, request, response);
			}
			else {
				int dvdId = Integer.parseInt(tempDvd);
				
				LoanDAO loanDAO = factory.getLoanDAO();
				Loan loan = loanDAO.getLoanById(dvdId, user.getId());
				
				loan.setEnd(date);
				loanDAO.update(loan);
				
				String message = "Vous venez de rendre le DVD <b>" + loan.getDVDTitle() + "</b>. Merci.";
				super.forward("", "message", message, request, response);
			}
		}
		catch(Exception e) {
			String error = "Le DVD n'a pas pu &ecirc;tre rendu, une erreur est survenue: " + e.toString();
			super.forward("", "error", error, request, response);
		}
	}
}

