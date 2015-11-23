package com.javachip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javachip.rmi.RMI_Main;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/confirmFile/*", loadOnStartup = 1, asyncSupported = true)
// .../confirmFile/?fileName="file"

public class ConfirmFile extends HttpServlet {

	// Name of file on client side to be written to
	String local_fileName;
	// Client side server socket port to be started on
	int client_socket_port = MainServlet.client_socket_port;
	// IP address of the rmi server
	String AsteriskJava_IP = MainServlet.AsteriskJava_IP;
	// Useless, need to get rid of
	String serviceName = "RPC_FileRead";
	// Name of file on server side to be read from
	String remote_AsteriskSrcFilename;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Set response content type to be html
		response.setContentType("text/html");

		try {
			// Get response

			local_fileName = (String) request.getParameter("fileName").replace("\"", ""); 
			remote_AsteriskSrcFilename = (String) request.getParameter("fileName").replace("\"", "");
			
			// Client side server socket port to be started on
			client_socket_port = MainServlet.client_socket_port;
			// IP address of the rmi server
			AsteriskJava_IP = MainServlet.AsteriskJava_IP;

			// Create a new instance of RMI client to initiate file content
			// transfer
			RMI_Main demo_instance = new RMI_Main();
			
			demo_instance.initialize(local_fileName, client_socket_port, AsteriskJava_IP, serviceName,
					remote_AsteriskSrcFilename);

			if (!demo_instance.getFinished())
				throw new Exception("Failed to get the file from server");
			else
				setFileRequest(request);

		} catch (Exception e) {
			e.printStackTrace();

			// Return to the browser this..
			PrintWriter out = response.getWriter();
			out.println("<h1> An error occured. The server could not get the contents the file you requested. </h1>");
		}

		try {
			Cookie cookie = new Cookie("MyCookie", local_fileName);
		    response.addCookie(cookie);
			getServletConfig().getServletContext().getRequestDispatcher("/ConfirmFile.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();

			// Return to the browser this..
			PrintWriter out = response.getWriter();
			out.println("<h1> Hello. The server could not get the page you requested. </h1>");
		}
	}

	// Possibly retrieve file contents directly from jsp
	void setFileRequest(HttpServletRequest request) {
		// This will reference one line at a time
		String line = null;
		String fileContent = "";
		
		try {
			local_fileName = local_fileName.replace("\"", "");

			File file = new File(local_fileName);

			System.out.println("File is found here: " + file.getAbsolutePath());

			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(file);
			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
				fileContent += line + "<br>";
			}

			System.out.println(fileContent);
			
			bufferedReader.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		request.setAttribute("confirmFile", fileContent);
	}

}
