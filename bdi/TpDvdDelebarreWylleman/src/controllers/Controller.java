package controllers;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public abstract class Controller extends HttpServlet {
    
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

