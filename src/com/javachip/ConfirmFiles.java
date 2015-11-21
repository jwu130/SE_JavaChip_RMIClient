package com.javachip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet implementation class ConfirmFiles
 */
@WebServlet("/ConfirmFiles")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB
maxFileSize = 1024 * 1024 * 50, // 50 MB
maxRequestSize = 1024 * 1024 * 100) // 100 MB

public class ConfirmFiles extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Name of file on client side to be written to
	String file1, file2;
	// Client side server socket port to be started on
	int client_socket_port = MainServlet.client_socket_port;
	// IP address of the rmi server
	String AsteriskJava_IP = MainServlet.AsteriskJava_IP;
	// Useless, need to get rid of
	String serviceName = "RPC_FileRead";
	// Name of file on server side to be read from
	String remote_AsteriskSrcFilename;
	// Directory Name
	String directory = "rmiclientfiles";

	//
	// // Set response content type to be html
	// response.setContentType("text/html");
	//
	// try {
	// file1 = (String) request.getParameter("fileName").replace("\"", "");
	// remote_AsteriskSrcFilename = (String)
	// request.getParameter("fileName").replace("\"", "");
	//
	// // Client side server socket port to be started on
	// client_socket_port = MainServlet.client_socket_port;
	// // IP address of the rmi server
	// AsteriskJava_IP = MainServlet.AsteriskJava_IP;
	//
	// // Create a new instance of RMI client to initiate file content
	// // transfer
	// RMI_Main demo_instance = new RMI_Main(file1, client_socket_port,
	// AsteriskJava_IP, serviceName,
	// remote_AsteriskSrcFilename);
	//
	// int count = 5;
	// while (!demo_instance.getFinished() && count > 0) {
	// long start = System.currentTimeMillis();
	// long end = start + 1 * 1000; // 1 seconds * 500 ms/sec
	// while (System.currentTimeMillis() < end) {
	// }
	// count--;
	// }
	//
	// if (!demo_instance.getFinished())
	// throw new Exception("failed to get the file from server");
	// else
	// setFileRequest(request);
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	//
	// // Return to the browser this..
	// PrintWriter out = response.getWriter();
	// out.println("<h1> An error occured. The server could not get the contents
	// the file you requested. </h1>");
	// }
	//
	// try {
	// getServletConfig().getServletContext().getRequestDispatcher("/confirmFile.jsp").forward(request,
	// response);
	// } catch (Exception e) {
	// e.printStackTrace();
	//
	// // Return to the browser this..
	// PrintWriter out = response.getWriter();
	// out.println("<h1> Hello. The server could not get the page you requested.
	// </h1>");
	// }

	void setFileRequest(HttpServletRequest request, String attributeName) {
		// This will reference one line at a time
		String line = null;
		String fileContent = "";

		try {

			file1 = file1.replace("\"", "");

			File dir = new File(directory);

			if (!dir.isDirectory())
				throw new Exception("No folder " + directory + " found");

			System.out.println("Folder is found in: " + dir.getAbsolutePath());

			File file = new File(dir, file1);

			System.out.println("File is found here: " + file.getAbsolutePath());

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

		request.setAttribute(attributeName, fileContent);
	}

}
