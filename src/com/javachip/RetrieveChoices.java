package com.javachip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.javachip.rmi.RMI_Main;

/**
 * Servlet implementation class saveUpload 
 * Requires a upload file and a "fileName" to retrieve from rmi server
 */

@WebServlet("/RetrieveChoices/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB
maxFileSize = 1024 * 1024 * 50, // 50 MB
maxRequestSize = 1024 * 1024 * 100) // 100 MB

public class RetrieveChoices extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Useless, need to get rid of
	String serviceName = "RPC_FileRead";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		try {
			// Set response content type to be html
			response.setContentType("text/html");
			
			String uploadedFile = "";
			saveUpload(request, response, uploadedFile);
			
			String local_fileName = "";
			saveFileFromServer(request, response, local_fileName);

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			getServletConfig().getServletContext().getRequestDispatcher("/ViewBothFiles.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void saveUpload(HttpServletRequest request, HttpServletResponse response, String uploadedFile)
			throws IllegalStateException, IOException, ServletException {
		String uploadFilePath = System.getProperty("user.dir");

		// creates the save directory if it does not exists
		File fileSaveDir = new File(uploadFilePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdirs();
		}
		System.out.println("Upload File Directory=" + fileSaveDir.getAbsolutePath());

		String fileName = "";
		// Get all the parts from request and write it to the file on server
		for (Part part : request.getParts()) {
			fileName = getFileName(part);
			if (fileName != "")
				part.write(uploadFilePath + File.separator + fileName);
		}
		
		uploadedFile = fileName;
	
		Util.setRequestAttr(request, fileName, "fileUploaded", "fileUploadedName");
		
		try {
			String srcFile = uploadFilePath + File.separator + fileName;
			System.out.println("This is the file location: " + srcFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Utility method to get file name from HTTP header content-disposition
	 */
	private String getFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		System.out.println("content-disposition header= " + contentDisp);
		String[] tokens = contentDisp.split(";");
		for (String token : tokens) {
			if (token.trim().startsWith("filename")) {
				return token.substring(token.indexOf("=") + 2, token.length() - 1);
			}
		}
		return "";
	}

	public void saveFileFromServer(HttpServletRequest request, HttpServletResponse response, String local_fileName) {
		try {
			local_fileName = (String) request.getParameter("fileName").replace("\"", "");

			// Client side server socket port to be started on
			int client_socket_port = MainServlet.client_socket_port;
			// IP address of the rmi server
			String AsteriskJava_IP = MainServlet.AsteriskJava_IP;

			// Create a new instance of RMI client to initiate file content
			// transfer
			RMI_Main demo_instance = new RMI_Main();
			demo_instance.initialize(local_fileName, client_socket_port, AsteriskJava_IP, serviceName, local_fileName);

			if (!demo_instance.getFinished())
				throw new Exception("Failed to get the file from server");
			else
				Util.setRequestAttr(request, local_fileName, "fileFromServer", "fileFromServerName");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
