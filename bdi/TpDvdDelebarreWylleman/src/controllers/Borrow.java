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

public class Borrow extends Controller {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			Date date = new Date(System.currentTimeMillis());
			String tempDvd = request.getParameter("dvdId");
			
			if (tempDvd == null || tempDvd.equals("")) {
				String error = "Emprunt de DVD annul&eacute;, le DVD n'est pas correct.";
				super.forward("loan", "error", error, request, response);
			}
			else {
				int dvdId = Integer.parseInt(tempDvd);
				
				LoanDAO loanDAO = factory.getLoanDAO();
				Loan loan = loanDAO.create(dvdId, user.getId(), date);
				
				DVDDAO dvdDAO = factory.getDVDDAO();
				DVD dvd = dvdDAO.getDVDById(dvdId);
				
				String message = "Vous venez d'emprunter le DVD <b>" + dvd.getTitle() + "</b>. Merci de le rendre avant <u>15 jours</u>.";
				super.forward("", "message", message, request, response);
			}
		}
		catch(Exception e) {
			String error = "Le DVD n'a pas pu &ecirc;tre emprunt&eacute;, une erreur est survenue: " + e.toString();
			super.forward("", "error", error, request, response);
		}
	}
}

