package com.javachip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.javachip.rmi.RMI_Main;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/confirmFile/*", loadOnStartup = 1, asyncSupported = true)

public class ConfirmFile extends HttpServlet {

	// Name of file on client side to be written to
	String local_fileName;
	// Client side server socket port to be started on
	int client_socket_port = MainServlet.client_socket_port;
	// IP address of the rmi server
	String AsteriskJava_IP = MainServlet.AsteriskJava_IP;
	// Useless, need to get rid of
	String serviceName = "retrieve_available_files";
	// Name of file on server side to be read from
	String remote_AsteriskSrcFilename = "txtServer";

	public void init(ServletConfig config) {
		System.out.println("ConfirmFile servlet has been initialized");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Set response content type to be html
		response.setContentType("text/html");

		try {
			// Get response

			local_fileName = (String) request.getParameter("fileName");

			// Client side server socket port to be started on
			client_socket_port = MainServlet.client_socket_port;
			// IP address of the rmi server
			AsteriskJava_IP = MainServlet.AsteriskJava_IP;

			// Create a new instance of RMI client to initiate file content
			// transfer
			RMI_Main demo_instance = new RMI_Main(local_fileName, client_socket_port, AsteriskJava_IP, serviceName,
					remote_AsteriskSrcFilename);

			int count = 5;
			while (!demo_instance.getFinished() && count > 0) {
				long start = System.currentTimeMillis();
				long end = start + 1 * 1000; // 1 seconds * 500 ms/sec
				while (System.currentTimeMillis() < end) {
				}
				count--;
			}

			if (!demo_instance.getFinished())
				throw new Exception("failed to get the file from server");
			else
				setFileRequest(request);

		} catch (Exception e) {
			e.printStackTrace();

			// Return to the browser this..
			PrintWriter out = response.getWriter();
			out.println("<h1> An error occured. The server could not get the contents the file you requested. </h1>");
		}

		try {
			getServletConfig()
			.getServletContext()
			.getRequestDispatcher("/confirmFile.jsp")
			.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();

			// Return to the browser this..
			PrintWriter out = response.getWriter();
			out.println("<h1> Hello. The server could not get the page you requested. </h1>");
		}

	}

	void setFileRequest(HttpServletRequest request) {
		// This will reference one line at a time
		String line = null;
		String fileContent = "";

		try {
			
			local_fileName = local_fileName.replace("\"", "");

			System.out.println("Folder is found in: " + System.getProperty("user.dir"));
			
			File file = new File(local_fileName);

			System.out.println("File is found in: " + file.getAbsolutePath());

			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(file);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
				fileContent += line;
			}

			// Always close files.
			bufferedReader.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		request.setAttribute("confirmFile", fileContent);
	}

}
