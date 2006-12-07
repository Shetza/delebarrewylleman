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
	
	/**
	 * La fabrique de DAO.
	 */
	private DAOFactory factory;
		
	/**
	 * Le contexte de la <code>Servlet</code>/
	 */
	private ServletContext context;
	
	public void init(ServletConfig config) throws ServletException {
		try {
			context = config.getServletContext();
			factory = (DAOFactory) context.getAttribute("factory");
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doGet(request, response);
	}
    
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String source = request.getParameter("source");
		try {
			List kinds = (List) context.getAttribute("kinds");
			if ( kinds == null || kinds.isEmpty() ) {
				System.out.println("Construction de la liste des categories.");
				KindDAO kindDAO = (KindDAO) factory.getDAO("kind");
				kinds = kindDAO.search(null);
				context.setAttribute("kinds", kinds);
			}
			super.forward(source, request, response);
		} catch(Exception e) {
			String error = "Impossible de composer la liste de cat&eacute;gories, une erreur est survenue: " + e.toString();
			super.forward(source, "error", error, request, response);
		}
	}
}

