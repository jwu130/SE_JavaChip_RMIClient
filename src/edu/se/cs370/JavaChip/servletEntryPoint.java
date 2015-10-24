package edu.se.cs370.JavaChip;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main")

public class servletEntryPoint extends HttpServlet{

	String message = "Hello World";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//do get request, response servlet exception, ioexception
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     // Set response content type
	      response.setContentType("text/html");

	      // Actual logic goes here.
	      PrintWriter out = response.getWriter();
	      out.println("<h1>" + message + "</h1>");
	}
	
	//do post request, response servlet exception, ioexception
	protected void doPost(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		//where to store file: good practice
		//get request servletcontext real path
		//regular expression \\\\ to escape two \
		//should not allow users to specify the file name
	}	
}
