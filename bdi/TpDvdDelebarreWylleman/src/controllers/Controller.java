package controllers;

import dao.business.KindDAO;
import dao.core.DAOFactory;
import dao.core.*;

import java.util.List;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public abstract class Controller extends HttpServlet {
	
	/**
	 * La fabrique de DAO.
	 */
	protected DAOFactory factory;
		
	/**
	 * Le contexte de la <code>Servlet</code>/
	 */
	protected ServletContext context;
	
	public void init(ServletConfig config) throws ServletException {
		try{
			// Recuperation du contexte.
			context = config.getServletContext();
			
			// Creation, si elle ne l'est pas, de la fabrique de DAO.
			factory = (DAOFactory) context.getAttribute("factory");
			if ( factory == null ) {
				System.out.println("Construction de la fabrique de DAO en cours.");
				String factoryType = context.getInitParameter("factory");
				Class c = Class.forName(factoryType);
				factory = (DAOFactory) c.newInstance();
				
				if (factory == null) throw new Exception("Boom - No DAOFactory");
				context.setAttribute("factory", factory);
				System.out.println("Construction de la fabrique de DAO finie.");
			}
			
			// Creation, si elle ne l'est pas, de la liste des categories.
			List kinds = (List) context.getAttribute("kinds");
			if ( kinds == null  ) {
				System.out.println("Construction de la liste des categories en cours.");
				KindDAO kindDAO = (KindDAO) factory.getDAO("kind");
				kinds = kindDAO.search(null);
				
				if (kinds == null) throw new Exception("Boom - No kind");
				context.setAttribute("kinds", kinds);
				System.out.println("Construction de la liste des categories finie.");
			}
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
    
	protected void forward(String source, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("/view.jsp?action="+source).forward(request, response);
	}
	
	protected void forward(String source, String type, Object message, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setAttribute(type, message);
		request.getRequestDispatcher("/view.jsp?action="+source).forward(request, response);
	}
	
	protected void forward(String source, String type, Object message, String name, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setAttribute(type, message);
		request.getRequestDispatcher("/view.jsp?action="+source+"&name="+name).forward(request, response);
	}
}

