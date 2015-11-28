package com.javachip;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
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
		String temp;
		boolean good = true;
		Scanner reader = null;

		try {
			System.out.print("Enter socket port for rmi client:");
			reader = new Scanner(System.in); // Reading from System.in
			temp = reader.next();
			if (!ValidationUtil.validSocketPort(temp)) {
				good = false;
				for (int i = 0; i < 3; i++) {
					System.out.print("Invalid socket port number. Try again: ");
					temp = reader.next();
					if (ValidationUtil.validSocketPort(temp)) {
						good = true;
						client_socket_port = Integer.parseInt(temp);
						break;
					}
				}
			} else
				client_socket_port = Integer.parseInt(temp);

			if (!good)
				throw new UnavailableException("Failed to retrieve socket port number.");

			System.out.print("Enter the ip address of the rmi server:");
			AsteriskJava_IP = reader.next();
			if (!ValidationUtil.validIP(AsteriskJava_IP)) {
				good = false;
				for (int i = 0; i < 3; i++) {
					System.out.print("Invalid IP address. Try again: ");
					AsteriskJava_IP = reader.next();
					if (ValidationUtil.validIP(AsteriskJava_IP)) {
						good = true;
						break;
					}
				}
			}

			if (!good)
				throw new UnavailableException("Failed to retrieve rmi server ip address.");

		} finally {
			if (reader != null)
				reader.close();
		}
		System.out.println("This is the base directory: " + System.getProperty("user.dir"));
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			getServletConfig().getServletContext().getRequestDispatcher("/Main.jsp").forward(request, response);
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
