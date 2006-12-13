package controllers;

import dao.core.*;
import dao.business.DVDDAO;
import dao.business.LoanDAO;

import models.Loan;
import models.DVD;
import models.User;

import java.util.Date;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class Reserve extends Controller {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			User user = (User) context.getAttribute("user");
			String tempDvd = request.getParameter("dvdId");
			
			if (tempDvd == null || tempDvd.equals("")) {
				String error = "R&eacute;serve de DVD annul&eacute;, le DVD n'est pas correct.";
				super.forward("", "error", error, request, response);
			}
			else {
				int dvdId = Integer.parseInt(tempDvd);
				
				LoanDAO loanDAO = factory.getLoanDAO();
				Loan loan = loanDAO.getLoanById(dvdId, 0);
				//loanDAO.reserve(dvdId, user.getId());
				loan.setReserveUser(user.getId());
				loanDAO.update(loan);
				
				DVDDAO dvdDAO = factory.getDVDDAO();
				DVD dvd = dvdDAO.getDVDById(dvdId);
				
				String message = "Vous venez de r&eacute;server le DVD <b>" + dvd.getTitle() + "</b>.\n<br> Il sera automatiquement emprunter pour vous d&egrave;s qu'il sera de nouveau disponible.";
				super.forward("", "message", message, request, response);
			}
		}
		catch(Exception e) {
			String error = "Le DVD n'a pas pu &ecirc;tre r&eacute;serv&eacute;, une erreur est survenue: " + e.toString();
			super.forward("", "error", error, request, response);
		}
	}
}

