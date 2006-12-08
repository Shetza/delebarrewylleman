package controllers;

import dao.business.KindDAO;
import dao.core.*;

import java.util.List;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class PrepareKinds extends Controller {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doGet(request, response);
	}
    
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String source = request.getParameter("source");
		try {
			List kinds = (List) context.getAttribute("kinds");
			if ( kinds == null || kinds.isEmpty() ) {
				System.out.println("Construction de la liste des categories en cours.");
				KindDAO kindDAO = (KindDAO) factory.getDAO("kind");
				kinds = kindDAO.search(null);
				context.setAttribute("kinds", kinds);
				System.out.println("Construction de la liste des categories finie.");
			}
			super.forward(source, request, response);
		} catch(Exception e) {
			String error = "Impossible de composer la liste de cat&eacute;gories, une erreur est survenue: " + e.toString();
			super.forward(source, "error", error, request, response);
		}
	}
}

