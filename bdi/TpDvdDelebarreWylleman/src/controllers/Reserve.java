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
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

public class Reserve extends Controller {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			String tempDvd = request.getParameter("dvdId");
			
			if (tempDvd == null || tempDvd.equals("")) {
				String error = "R&eacute;serve de DVD annul&eacute;, le DVD n'est pas correct.";
				super.forward("", "error", error, request, response);
			}
			else {
				int dvdId = Integer.parseInt(tempDvd);
				
				LoanDAO loanDAO = factory.getLoanDAO();
				Loan loan = loanDAO.getLoanById(dvdId, 0);
				
				if ( loan == null ) {
					String error = "R&eacute;serve de DVD annul&eacute;, le DVD ne peut pas &ecirc;tre r&eacute;serv&eacute; puisqu'il n'est pas emprunt&eacute;.";
					super.forward("", "error", error, request, response);
				}
				else if ( loan.getReserveUser() != 0 ) {
					String error = "R&eacute;serve de DVD annul&eacute;, le DVD est d&eacute;j&agrave; r&eacute;serv&eacute;.";
					super.forward("", "error", error, request, response);
				}
				else {
					loan.setReserveUser(user.getId());
					loanDAO.update(loan);
					
					DVDDAO dvdDAO = factory.getDVDDAO();
					DVD dvd = dvdDAO.getDVDById(dvdId);
					
					String message = "Vous venez de r&eacute;server le DVD <b>" + dvd.getTitle() + "</b>.\n<br/> Il sera automatiquement emprunter pour vous d&egrave;s qu'il sera de nouveau disponible.";
					
					if ( loanDAO.getLoans(user).size() > 2 ) message += "\n<br/><br/>ATTENTION: Vous avez actuellement 3 DVDs &agrave; l'emprunt. Le DVD que vous venez de reserver ne sera pas emprunt&eacute; automatiquement tant que vous ne rendrez pas un de vos 3 emprunts.";
					
					super.forward("", "message", message, request, response);
				}
			}
		}
		catch(Exception e) {
			String error = "Le DVD n'a pas pu &ecirc;tre r&eacute;serv&eacute;, une erreur est survenue: " + e.toString();
			super.forward("", "error", error, request, response);
		}
	}
}

