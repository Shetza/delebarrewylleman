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

public class CancelReserve extends Controller {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			User user = (User) context.getAttribute("user");
			String tempDvd = request.getParameter("dvdId");
			
			if (tempDvd == null || tempDvd.equals("")) {
				String error = "Annulation de r&eacute;servation de DVD annul&eacute;e, le DVD n'est pas correct.";
				super.forward("", "error", error, request, response);
			}
			else {
				int dvdId = Integer.parseInt(tempDvd);
				
				LoanDAO loanDAO = factory.getLoanDAO();
				Loan loan = loanDAO.getLoanById(dvdId, 0);
				
				loan.setReserveUser(0);
				loanDAO.update(loan);
				
				String message = "Vous venez d'annuler la r&eacute;servation du DVD <b>" + loan.getDVDTitle() + "</b>.";
				super.forward("", "message", message, request, response);
			}
		}
		catch(Exception e) {
			String error = "La r&eacute;servation du DVD n'a pas pu &ecirc;tre annul&eacute;e, une erreur est survenue: " + e.toString();
			super.forward("", "error", error, request, response);
		}
	}
}

