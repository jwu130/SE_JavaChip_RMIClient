package com.javachip;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class saveUpload
 * Requires a upload file and a name for "file2" already stored in rmiclientfiles 
 */
@WebServlet("/saveUpload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB
maxFileSize = 1024 * 1024 * 50, // 50 MB
maxRequestSize = 1024 * 1024 * 100) // 100 MB

public class ConfirmFiles extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Name of file uploaded
	String file;

	/**
	 * Directory where uploaded files will be saved, its relative to the web
	 * application directory.
	 */
	private static final String DIRECTORY_NAME = "rmiclientfiles";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// gets absolute path of the web application
		String applicationPath = request.getServletContext().getRealPath("");
		// constructs path of the directory to save uploaded file
		String uploadFilePath = applicationPath + File.separator + DIRECTORY_NAME;

		// creates the save directory if it does not exists
		File fileSaveDir = new File(uploadFilePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdirs();
		}
		System.out.println("Upload File Directory=" + fileSaveDir.getAbsolutePath());

		file = "";
		// Get all the parts from request and write it to the file on server
		for (Part part : request.getParts()) {
			file = getFileName(part);
			if (file != "")
				part.write(uploadFilePath + File.separator + file);
		}
		
//		file2 = request.getParameter("file2");

		try {
			String srcFile = uploadFilePath + File.separator + file;
			System.out.println("This is the file location: " + srcFile);
//			request.setAttribute("file1", file1);
//			request.setAttribute("file2", file2);
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

}
