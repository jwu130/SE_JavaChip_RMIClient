package com.javachip;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javachip.rmi.RMI_Main;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/serverFiles", loadOnStartup = 1)

public class ServerFiles extends HttpServlet {

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

		// Client side server socket port to be started on
		client_socket_port = MainServlet.client_socket_port;
		// IP address of the rmi server
		AsteriskJava_IP = MainServlet.AsteriskJava_IP;
		LinkedList<String> filesAvailable = null;

		if (client_socket_port != 0 || AsteriskJava_IP != null) {
			RMI_Main demo_instance = new RMI_Main(local_fileName, client_socket_port, AsteriskJava_IP, serviceName,
					remote_AsteriskSrcFilename);

			filesAvailable = demo_instance.availableFiles;

			long start = System.currentTimeMillis();
			long end = start + 5 * 1000; // 60 seconds * 1000 ms/sec
			while (System.currentTimeMillis() < end) {
			}

			String s;
			ListIterator<String> it = filesAvailable.listIterator();
			while (it.hasNext()) {
				s = it.next();
			}

		} else {
			System.out.println("Client Socket Port and Server IP is not available");
		}

		if (filesAvailable != null)
			request.setAttribute("fileList", filesAvailable);

		try {
			getServletConfig().getServletContext().getRequestDispatcher("/testing.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();

			// Return to the browser this..
			out.println("<h1> Hello. The server could not get the page you requested. </h1>");
		}
	}
}
