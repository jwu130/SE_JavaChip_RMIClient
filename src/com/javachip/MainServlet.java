package com.javachip;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/main" }, loadOnStartup = 1)

public class MainServlet extends HttpServlet {

	// Test message to return in the html in the http response
	String message = "Hello World";

	// Name of file on client side to be written to
	String local_fileName = "txtClient"; // currently test value
	// Client side server socket port to be started on
	static int client_socket_port = 0;
	// IP address of the rmi server
	static String AsteriskJava_IP; // should be input
	// Useless, need to get rid of
	String serviceName = "retrieve_available_files";
	// Name of file on server side to be read from
	String remote_AsteriskSrcFilename = "txtServer";

	@Override
	public void init() throws ServletException {
		System.out.print("Enter socket port for rmi client:");
		Scanner reader = new Scanner(System.in); // Reading from System.in
		client_socket_port = Integer.parseInt(reader.next());
		System.out.print("Enter the ip address of the rmi server:");
		AsteriskJava_IP = reader.next();
		reader.close();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// // Set response content type to be html
		// response.setContentType("text/html");
		//
		// // Return to the browser this..
		// PrintWriter out = response.getWriter();
		// out.println("<h1>" + message + "</h1>");
		//
		//
		try {
			getServletConfig().getServletContext().getRequestDispatcher("/testing.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();

			// Return to the browser this..
			PrintWriter out = response.getWriter();
			out.println("<h1> Hello. The server could not get the page you requested. </h1>");
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
