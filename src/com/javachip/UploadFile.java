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
@WebServlet("/uploadFile")
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// Get the print writer
		PrintWriter out = response.getWriter();
		
		try {
			getServletConfig().getServletContext().getRequestDispatcher("/FileUpload.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();

			// Return to the browser this..
			out.println("<h1> Hello. The server could not get the page you requested. </h1>");
		}
		
	}

}
