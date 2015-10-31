import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLClassLoader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main")

public class servletEntryPoint extends HttpServlet {

	// Test message to return in the html in the http response
	String message = "Hello World";

	// Name of file on client side to be written to 
	String local_fileName = "txtClient"; // currently test value
	// Client side server socket port to be started on
	int client_socket_port = 7000;
	// IP address of the rmi server
	String AsteriskJava_IP = "192.168.1.8"; // should be input
	// Useless, need to get rid of
	String Service_UID = "Anything";
	// Name of file on server side to be read from 
	String remote_AsteriskSrcFilename = "txtServer";

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set response content type to be html
		response.setContentType("text/html");

		// Return to the browser this..
		PrintWriter out = response.getWriter();
		out.println("<h1>" + message + "</h1>");

		// Create a new instance of RMI client to initiate file content transfer
		RMI_BioAPI_Demo demo_instance = new RMI_BioAPI_Demo(local_fileName, client_socket_port, AsteriskJava_IP,
				Service_UID, remote_AsteriskSrcFilename);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
