package com.javachip;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javachip.util.Util;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/FileChoices", loadOnStartup = 1)

public class FileChoices extends HttpServlet {

	// Name of file on client side to be written to
	String local_fileName = "ServerFilesList"; // Not needed
	// Client side server socket port to be started on
	int client_socket_port = MainServlet.client_socket_port;
	// IP address of the rmi server
	String AsteriskJava_IP = MainServlet.AsteriskJava_IP;
	// Useless, need to get rid of
	String serviceName = "retrieve_available_files";
	// Name of file on server side to be read from
	String remote_AsteriskSrcFilename = "txtServer";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Set response content type to be html
		response.setContentType("text/html");

		// Return to the browser this..
		PrintWriter out = response.getWriter();
		
		getServerFiles(request, response, out);

		try {
			getServletConfig().getServletContext().getRequestDispatcher("/MainPage.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			out.println("<h1> Hello. The server could not get the page you requested. </h1>");
		}
	}

	public void getServerFiles(HttpServletRequest request, HttpServletResponse response, PrintWriter out){
		LinkedList filesAvailable = null;
		
		// Client side server socket port to be started on
		int client_socket_port = MainServlet.client_socket_port;
		// IP address of the rmi server
		String AsteriskJava_IP = MainServlet.AsteriskJava_IP;
		
		if (client_socket_port != 0 || AsteriskJava_IP != null) {
			filesAvailable = Util.getAvailableFiles();
			
		} else {
			System.out.println("Client Socket Port and Server IP is not available");
			out.println("<h1> Server error </h1>");
		}

		if (filesAvailable != null)
			request.setAttribute("fileList", filesAvailable);
	}
}
