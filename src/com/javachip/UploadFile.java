package com.javachip;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UploadFile
 */
@WebServlet("/UploadFile")
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String confirm = request.getParameter("confirm");
		
		// Set response content type to be html
		response.setContentType("text/html");
		// Return to the browser this..
		PrintWriter out = response.getWriter();
		
		if (confirm.equals("no")) {
			try {
				getServletConfig().getServletContext().getRequestDispatcher("/serverFiles").forward(request,
						response);
			} catch (Exception e) {
				e.printStackTrace();

				// Return to the browser this..
				out.println("<h1> Hello. The server could not get the page you requested. </h1>");
			}
		} else {
			try {
				getServletConfig().getServletContext().getRequestDispatcher("/UploadFile.jsp").forward(request,
						response);
			} catch (Exception e) {
				e.printStackTrace();

				// Return to the browser this..
				out.println("<h1> Hello. The server could not get the page you requested. </h1>");
			}
		}
	}

}
