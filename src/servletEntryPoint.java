import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/main"}, loadOnStartup=1)

public class servletEntryPoint extends HttpServlet {

	// Test message to return in the html in the http response
	String message = "Hello World";

	// Name of file on client side to be written to
	String local_fileName = "txtClient"; // currently test value
	// Client side server socket port to be started on
	int client_socket_port = 0;
	// IP address of the rmi server
	String AsteriskJava_IP; // should be input
	// Useless, need to get rid of
	String serviceName = "retrieve_available_files";
	// Name of file on server side to be read from
	String remote_AsteriskSrcFilename = "txtServer";

	@Override
	public void init() throws ServletException {
//		client_socket_port = Integer.parseInt(System.console().readLine());

		System.out.print("Enter socket port for rmi client:");
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		client_socket_port = Integer.parseInt(reader.next());
		System.out.println(client_socket_port);
		System.out.print("Enter the ip address of the rmi server:");
		AsteriskJava_IP = reader.next();
		System.out.println(AsteriskJava_IP);
		reader.close();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set response content type to be html
		response.setContentType("text/html");

		// Return to the browser this..
		PrintWriter out = response.getWriter();
		out.println("<h1>" + message + "</h1>");

		if (client_socket_port != 0 || AsteriskJava_IP != null) {
			RMI_Main demo_instance = new RMI_Main(local_fileName, client_socket_port, AsteriskJava_IP,
					serviceName, remote_AsteriskSrcFilename);

			LinkedList<String> filesAvailable = demo_instance.availableFiles;

			long start = System.currentTimeMillis();
			long end = start + 5 * 1000; // 60 seconds * 1000 ms/sec
			while (System.currentTimeMillis() < end) {
			}

			String s;
			ListIterator<String> it = filesAvailable.listIterator();
			while (it.hasNext()) {
				s = it.next();
				out.println(s);
			}
		}
		// Create a new instance of RMI client to initiate file content transfer
		// RMI_BioAPI_Demo demo_instance = new RMI_BioAPI_Demo(local_fileName,
		// client_socket_port, AsteriskJava_IP,
		// Service_UID, remote_AsteriskSrcFilename);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
